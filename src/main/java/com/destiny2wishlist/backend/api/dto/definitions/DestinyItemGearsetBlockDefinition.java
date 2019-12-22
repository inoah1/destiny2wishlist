package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemGearsetBlockDefinition {

    @JsonProperty("trackingValueMax")
    private Integer trackingValueMax;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyInventoryItemDefinition
     */
    @JsonProperty("itemList")
    private Long[] itemList;
}
