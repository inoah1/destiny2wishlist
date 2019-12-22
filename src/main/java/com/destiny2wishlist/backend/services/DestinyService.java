package com.destiny2wishlist.backend.services;

import com.destiny2wishlist.backend.api.dto.DestinyManifest;
import com.destiny2wishlist.backend.api.dto.DestinyManifestInfo;
import com.destiny2wishlist.backend.api.dto.DestinyManifestResponse;
import com.destiny2wishlist.backend.api.dto.definitions.*;
import com.destiny2wishlist.backend.api.dto.definitions.common.DestinyDisplayPropertiesDefinition;
import com.destiny2wishlist.backend.api.dto.definitions.enums.DestinyItemTypeEnum;
import com.destiny2wishlist.backend.api.dto.definitions.sockets.DestinyPlugSetDefinition;
import com.destiny2wishlist.backend.api.dto.utils.AppConstants;
import com.destiny2wishlist.backend.api.exception.ApiClientException;
import com.destiny2wishlist.backend.entities.DestinyManifestEntity;
import com.destiny2wishlist.backend.entities.DestinyWeapon;
import com.destiny2wishlist.backend.entities.DestinyWeaponSocket;
import com.destiny2wishlist.backend.repositories.DestinyManifestRepository;
import com.destiny2wishlist.backend.repositories.DestinyWeaponRepository;
import com.destiny2wishlist.backend.repositories.DestinyWeaponSocketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class DestinyService {

    private final BungieApiClient bungieApiClient;

    private final DestinyManifestRepository manifestRepository;

    private final DestinyWeaponRepository weaponRepository;

    private final DestinyWeaponSocketRepository weaponSocketRepository;

    @Value("${bungie.host}")
    private String apiRoot;

    public DestinyService(BungieApiClient bungieApiClient, DestinyManifestRepository manifestRepository, DestinyWeaponRepository weaponRepository, DestinyWeaponSocketRepository weaponSocketRepository) {
        this.bungieApiClient = bungieApiClient;
        this.manifestRepository = manifestRepository;
        this.weaponRepository = weaponRepository;
        this.weaponSocketRepository = weaponSocketRepository;

        try {
            loadDestinyManifest();
        } catch (ApiClientException e) {
            log.error("Error while retrieving Destiny Manifest", e);
        }
    }

    /**
     * Call Bungie Api and get latest manifest data
     *
     * @throws ApiClientException - can occur when bungie api is unresponsive
     */
    public void loadDestinyManifest() throws ApiClientException {

        DestinyManifestEntity latestManifest = manifestRepository.findTopByOrderByUpdatedAtDesc();
        if (latestManifest == null) {
            latestManifest = new DestinyManifestEntity();
        }

        // Only want to call bungie api once per day
        if (latestManifest.getUpdatedAt() == null || LocalDate.now().isAfter(latestManifest.getUpdatedAt().toLocalDate())) {
            log.debug("Getting latest destiny manifest version");
            DestinyManifestResponse manifestResponse = bungieApiClient.getManifestInfo();

            if (manifestResponse != null) {
                DestinyManifestInfo manifestInfo = manifestResponse.getResponse();

                if (manifestInfo != null) {
                    if (!manifestInfo.getVersion().equals(latestManifest.getVersion())) {
                        // refresh manifest info
                        refreshDestinyManifest(manifestInfo);
                    } else {
                        latestManifest.setUpdatedAt(LocalDateTime.now());
                        manifestRepository.save(latestManifest);
                    }
                }
            }
        }
    }

    private void refreshDestinyManifest(DestinyManifestInfo manifestInfo) throws ApiClientException {
        log.debug("Getting latest Destiny Manifest");

        // Get latest manifest from Destiny api and persist to database
        DestinyManifest destinyManifest = bungieApiClient.getManifestJson(manifestInfo.getJsonWorldContentPaths().getEnJsonPath());
        DestinyManifestEntity destinyManifestEntity = new DestinyManifestEntity();
        destinyManifestEntity.setVersion(manifestInfo.getVersion());
        destinyManifestEntity.setEnJsonPath(manifestInfo.getJsonWorldContentPaths().getEnJsonPath());
        manifestRepository.save(destinyManifestEntity);

        // DestinyInventoryItemDefinition manifest entry
        LinkedHashMap<Long, DestinyInventoryItemDefinition> inventoryItemDefinitionsMap = destinyManifest.getDestinyInventoryItemDefinition();
        // DestinyItemCategoryDefinition manifest entry
        LinkedHashMap<Long, DestinyItemCategoryDefinition> destinyItemCategoryDefinitionMap = destinyManifest.getDestinyItemCategoryDefinition();
        // DestinyPlugSetDefinition manifest entry
        LinkedHashMap<Long, DestinyPlugSetDefinition> destinyPlugSetDefinitionMap = destinyManifest.getDestinyPlugSetDefinition();

        if (inventoryItemDefinitionsMap != null && !inventoryItemDefinitionsMap.isEmpty()) {
            deleteAllWeaponsInfo();

            inventoryItemDefinitionsMap.forEach((key, definition) -> {
                //TODO handle dummy weapons - check for Dummy item category - 3109687656 in item category hash
                if (isValidWeapon(definition, destinyItemCategoryDefinitionMap)) {

                    DestinyWeapon destinyWeapon = createDestinyWeapon(definition);

                    setWeaponSockets(destinyWeapon, definition.getSockets(), destinyPlugSetDefinitionMap, destinyManifest.getDestinyInventoryItemDefinition());

                    weaponRepository.save(destinyWeapon);
                }
            });
        }
    }

    private DestinyWeapon createDestinyWeapon(DestinyInventoryItemDefinition definition) {
        DestinyWeapon destinyWeapon;
        Optional<DestinyWeapon> byId = weaponRepository.findById(definition.getHash());
        destinyWeapon = byId.orElseGet(DestinyWeapon::new);

        destinyWeapon.setHash(definition.getHash());
        destinyWeapon.setItemSubType(definition.getItemSubType());

        setWeaponDisplayProperties(destinyWeapon, definition.getDisplayProperty());

        return destinyWeapon;
    }

    private void setWeaponSockets(DestinyWeapon destinyWeapon, DestinyItemSocketBlockDefinition sockets, LinkedHashMap<Long, DestinyPlugSetDefinition> destinyPlugSetDefinitionMap, LinkedHashMap<Long, DestinyInventoryItemDefinition> destinyInventoryItemDefinitionMap) {
        if (sockets != null) {
            DestinyItemSocketEntryDefinition[] socketEntries = sockets.getSocketEntries();
            if (socketEntries != null) {
                for (int i = 0; i < socketEntries.length; i++) {
                    if (i < 5) {
                        // set weapon perk in appropriate slot
                        setSocketSlot(i, destinyWeapon, socketEntries[i], destinyPlugSetDefinitionMap, destinyInventoryItemDefinitionMap);
                    }

                }
            }
        }
    }

    private void setSocketSlot(int slot, DestinyWeapon destinyWeapon, DestinyItemSocketEntryDefinition socketEntry, LinkedHashMap<Long, DestinyPlugSetDefinition> destinyPlugSetDefinitionMap, LinkedHashMap<Long, DestinyInventoryItemDefinition> destinyInventoryItemDefinitionMap) {
        long plugSetHash = 0L;
        if (socketEntry.getReusablePlugSetHash() != null) {
            plugSetHash = socketEntry.getReusablePlugSetHash();
        } else if (socketEntry.getRandomizedPlugSetHash() != null) {
            plugSetHash = socketEntry.getRandomizedPlugSetHash();
        }

        if (plugSetHash > 0) {
            // get plug set definition
            DestinyPlugSetDefinition destinyPlugSetDefinition = destinyPlugSetDefinitionMap.get(plugSetHash);

            if (destinyPlugSetDefinition != null && destinyPlugSetDefinition.getReusablePlugItems() != null) {
                Set<DestinyWeaponSocket> perkList = new HashSet<>();
                DestinyItemSocketEntryPlugItemRandomizedDefinition[] reusablePlugItems = destinyPlugSetDefinition.getReusablePlugItems();
                if (reusablePlugItems != null) {
                    for (DestinyItemSocketEntryPlugItemRandomizedDefinition reusablePlugItem : reusablePlugItems) {
                        // Get inventory item definition
                        DestinyInventoryItemDefinition definition = destinyInventoryItemDefinitionMap.get(reusablePlugItem.getPlugItemHash());
                        if (definition != null) {
                            DestinyWeaponSocket destinyWeaponSocket = createDestinyWeaponSocket(definition);

                            weaponSocketRepository.save(destinyWeaponSocket);

                            perkList.add(destinyWeaponSocket);
                        }
                    }
                }

                switch (slot) {
                    case 0:
                        destinyWeapon.setIntrinsicPerk(perkList);
                        break;
                    case 1:
                        destinyWeapon.setBarrel(perkList);
                        break;
                    case 2:
                        destinyWeapon.setMagazine(perkList);
                        break;
                    case 3:
                        destinyWeapon.setFirstPerk(perkList);
                        break;
                    case 4:
                        destinyWeapon.setSecondPerk(perkList);
                        break;
                    default:
                        log.error("Invalid slot - " + slot + " found for weapon - " + destinyWeapon.getName());
                }
            }
        }
    }

    private DestinyWeaponSocket createDestinyWeaponSocket(DestinyInventoryItemDefinition definition) {
        DestinyWeaponSocket destinyWeaponPerk = new DestinyWeaponSocket();
        destinyWeaponPerk.setHash(definition.getHash());

        if (definition.getDisplayProperty() != null) {
            destinyWeaponPerk.setName(definition.getDisplayProperty().getName());
        }

        return destinyWeaponPerk;
    }

    /**
     * Set weapons display properties
     *
     * @param destinyWeapon - persistence entity representing a destiny weapon
     * @param definition    - manifest definition for a destiny weapon
     */
    private void setWeaponDisplayProperties(DestinyWeapon destinyWeapon, DestinyDisplayPropertiesDefinition definition) {
        if (definition != null) {
            destinyWeapon.setName(definition.getName());
            destinyWeapon.setDescription(definition.getDescription());
            destinyWeapon.setIcon(definition.getIcon());
        }
    }

    /**
     * Performs various checks to determine if inventory item is a weapon
     *
     * @param definition                       - inventory item
     * @param destinyItemCategoryDefinitionMap - map containing all item category definitions
     * @return true if item is a weapon else false
     */
    private boolean isValidWeapon(DestinyInventoryItemDefinition definition, LinkedHashMap<Long, DestinyItemCategoryDefinition> destinyItemCategoryDefinitionMap) {
        boolean hasWeaponCategory = false;
        boolean hasWeaponType = false;
        boolean isEquippable = false;

        // Check if itemType is Weapon
        if (DestinyItemTypeEnum.WEAPON == definition.getItemType()) {
            hasWeaponType = true;
        }

        // Check if item has Weapon item category
        Long[] itemCategoryHashes = definition.getItemCategoryHashes();
        if (itemCategoryHashes != null) {
            for (Long itemCategoryHash : itemCategoryHashes) {
                DestinyItemCategoryDefinition destinyItemCategoryDefinition = destinyItemCategoryDefinitionMap.get(itemCategoryHash);
                if (destinyItemCategoryDefinition.getDisplayProperty() != null) {
                    if (AppConstants.WEAPON.equalsIgnoreCase(destinyItemCategoryDefinition.getDisplayProperty().getName())) {
                        hasWeaponCategory = true;
                    }
                }
            }
        }

        if (definition.getEquippable()) {
            isEquippable = true;
        }

        return (hasWeaponCategory && hasWeaponType && isEquippable);
    }

    public List<DestinyWeapon> getAllWeapons() {
        return StreamSupport.stream(weaponRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    private void deleteAllWeaponsInfo() {
        weaponRepository.deleteAll();
        weaponSocketRepository.deleteAll();
    }

    public List<DestinyWeapon> findWeapon(String weaponName) {
        return weaponRepository.findByNameContainingIgnoreCase(weaponName);
    }
}
