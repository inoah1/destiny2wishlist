package com.destiny2wishlist.backend.api.dto.definitions;


import com.destiny2wishlist.backend.api.dto.definitions.utils.DyeReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemTranslationBlockDefinition {

    @JsonProperty("weaponPatternIdentifier")
    private String weaponPatternIdentifier;

    @JsonProperty("weaponPatternHash")
    private Long weaponPatternHash;

    @JsonProperty("defaultDyes")
    private DyeReference[] defaultDyes;

    @JsonProperty("lockedDyes")
    private DyeReference[] lockedDyes;

    @JsonProperty("customDyes")
    private DyeReference[] customDyes;

    @JsonProperty("arrangements")
    private DestinyGearArtArrangementReference[] arrangements;

    @JsonProperty("hasGeometry")
    private Boolean hasGeometry;
}
