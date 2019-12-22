package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemIntrinsicSocketEntryDefinition {

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyInventoryItemDefinition
     */
    @JsonProperty("plugItemHash")
    private Long plugItemHash;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.Sockets.DestinySocketTypeDefinition
     */
    @JsonProperty("socketTypeHash")
    private Long socketTypeHash;

    @JsonProperty("defaultVisible")
    private Boolean defaultVisible;
}
