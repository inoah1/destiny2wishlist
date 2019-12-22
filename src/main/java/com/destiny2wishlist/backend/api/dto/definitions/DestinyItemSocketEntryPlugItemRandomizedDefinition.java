package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemSocketEntryPlugItemRandomizedDefinition {

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyInventoryItemDefinition
     */
    @JsonProperty("plugItemHash")
    private Long plugItemHash;

    @JsonProperty("weight")
    private Float weight;

    @JsonProperty("alternateWeight")
    private Float alternateWeight;
}
