package com.destiny2wishlist.backend.services;

import com.destiny2wishlist.backend.api.dto.DestinyInventoryItemDefinition;
import com.destiny2wishlist.backend.api.dto.DestinyManifestInfo;
import com.destiny2wishlist.backend.api.dto.DestinyManifestJson;
import com.destiny2wishlist.backend.api.dto.DestinyManifestResponse;
import com.destiny2wishlist.backend.api.dto.enums.DestinyItemTypeEnum;
import com.destiny2wishlist.backend.api.exception.ApiClientException;
import com.destiny2wishlist.backend.entities.DestinyManifest;
import com.destiny2wishlist.backend.entities.DestinyWeapon;
import com.destiny2wishlist.backend.entities.enums.DestinyItemCategoryEnum;
import com.destiny2wishlist.backend.repositories.DestinyManifestRepository;
import com.destiny2wishlist.backend.repositories.DestinyWeaponRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DestinyManifestService {

    private final DestinyApiClient destinyApiClient;

    private final DestinyManifestRepository manifestRepository;

    private final DestinyWeaponRepository weaponRepository;

    @Value("${bungie.host}")
    private String apiRoot;

    private LocalDate lastRefresh;

    public DestinyManifestService(DestinyApiClient destinyApiClient, DestinyManifestRepository manifestRepository, DestinyWeaponRepository weaponRepository) {
        this.destinyApiClient = destinyApiClient;
        this.manifestRepository = manifestRepository;
        this.weaponRepository = weaponRepository;
    }

    public void loadDestinyManifest() throws ApiClientException {

        //Only call api once per day
        if (lastRefresh == null || LocalDate.now().isAfter(lastRefresh)) {
            DestinyManifestResponse manifestResponse = destinyApiClient.getManifestInfo();

            if (manifestResponse != null) {
                DestinyManifestInfo manifestInfo = manifestResponse.getResponse();

                if (manifestInfo != null) {
                    List<DestinyManifest> latestManifestCollection = manifestRepository.findLatestManifest();
                    if (latestManifestCollection != null && !latestManifestCollection.isEmpty()) {
                        // If manifest version already exists in DB, then do nothing
                        if (manifestInfo.getVersion().equals(latestManifestCollection.get(0).getVersion())) {
                            return;
                        }
                    }

                    // Get latest manifest from Destiny api and persist
                    DestinyManifestJson manifestJson = destinyApiClient.getManifestJson(manifestInfo.getJsonWorldContentPaths().getEnJsonPath());
                    DestinyManifest destinyManifest = new DestinyManifest(manifestInfo.getVersion(), manifestJson.getJsonData());
                    manifestRepository.save(destinyManifest);
                    lastRefresh = LocalDate.now();

                    LinkedHashMap<String, DestinyInventoryItemDefinition> inventoryItemDefinitionsMap = manifestJson.getInventoryItemDefinitions();

                    //Delete current weapons
                    deleteAllWeaponsInfo();

                    inventoryItemDefinitionsMap.forEach((key, definition) -> {
                        if (definition.getItemType() == DestinyItemTypeEnum.WEAPON.getValue() && definition.isEquippable()) {
                            DestinyWeapon destinyWeapon = new DestinyWeapon();

                            destinyWeapon.setHash(definition.getHash());

                            // Get display properties
                            if (definition.getDisplayProperties() != null) {
                                destinyWeapon.setName(definition.getDisplayProperties().getName());
                                destinyWeapon.setDescription(definition.getDisplayProperties().getDescription());
                                if (definition.getDisplayProperties().isHasIcon()) {
                                    destinyWeapon.setIcon(apiRoot + definition.getDisplayProperties().getIcon());
                                }
                            }

                            destinyWeapon.setItemType(definition.getItemType());
                            destinyWeapon.setItemSubType(definition.getItemSubType());

                            // Get Item categories
                            if (definition.getItemCategoryHashes() != null) {
                                for (int i = 0; i < definition.getItemCategoryHashes().length; i++) {
                                    Collection<DestinyItemCategoryEnum> itemCategoryEnumCollection = new ArrayList<>();
                                    itemCategoryEnumCollection.add(DestinyItemCategoryEnum.fromLong(definition.getItemCategoryHashes()[i]));

                                }
                            }

                            //TODO validate if weapon is valid and should be persisted
                            weaponRepository.save(destinyWeapon);
                        }
                    });
                }
            }
        }
    }

    public List<DestinyWeapon> getAllWeapons() {
        return StreamSupport.stream(weaponRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    private void deleteAllWeaponsInfo() {
        weaponRepository.deleteAll();
    }
}
