package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemTalentGridBlockDefinition {

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyTalentGridDefinition
     */
    @JsonProperty("talentGridHash")
    private Long talentGridHash;

    @JsonProperty("itemDetailString")
    private String itemDetailString;

    @JsonProperty("buildName")
    private String buildName;

    @JsonProperty("hudDamageType")
    private Integer hudDamageType;

    @JsonProperty("hudIcon")
    private String hudIcon;
}
