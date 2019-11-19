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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

@Service
public class LoadManifestService {

    @Autowired
    DestinyApiClient destinyApiClient;

    @Autowired
    private DestinyManifestRepository manifestRepository;

    @Autowired
    private DestinyWeaponRepository weaponRepository;

    @Value("${bungie.host}")
    private String apiRoot;

    public void loadDestinyManifest() throws ApiClientException {

        DestinyManifestResponse manifestResponse = destinyApiClient.getManifestInfo();

        if (manifestResponse != null) {
            DestinyManifestInfo manifestInfo = manifestResponse.getResponse();

            if (manifestInfo != null) {
                //TODO check if manifest already exists in DB
                //TODO if manifest exists and was last refreshed more than a day ago, refresh.
                DestinyManifestJson manifestJson = destinyApiClient.getManifestJson(manifestInfo.getJsonWorldContentPaths().getEnJsonPath());
                DestinyManifest destinyManifest = new DestinyManifest(manifestInfo.getVersion(), manifestJson.getJsonData());
                manifestRepository.save(destinyManifest);

                LinkedHashMap<String, DestinyInventoryItemDefinition> inventoryItemDefinitionsMap = manifestJson.getInventoryItemDefinitions();

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
