package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyEquippingBlockDefinition {

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyInventoryItemDefinition
     */
    @JsonProperty("gearsetItemHash")
    private Long gearsetItemHash;

    @JsonProperty("uniqueLabel")
    private String uniqueLabel;

    @JsonProperty("uniqueLabelHash")
    private Long uniqueLabelHash;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyEquipmentSlotDefinition
     */
    @JsonProperty("equipmentSlotTypeHash")
    private Long equipmentSlotTypeHash;

    @JsonProperty("attributes")
    private Integer attributes;

    @JsonProperty("equippingSoundHash")
    private Long equippingSoundHash;

    @JsonProperty("hornSoundHash")
    private Long hornSoundHash;

    @JsonProperty("ammoType")
    private Integer ammoType;

    @JsonProperty("displayStrings")
    private String[] displayStrings;
}
