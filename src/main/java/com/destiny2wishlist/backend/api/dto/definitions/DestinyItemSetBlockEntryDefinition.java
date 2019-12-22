package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemSetBlockEntryDefinition {

    @JsonProperty("trackingValue")
    private Integer trackingValue;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyInventoryItemDefinition
     */
    @JsonProperty("itemHash")
    private Long itemHash;
}
