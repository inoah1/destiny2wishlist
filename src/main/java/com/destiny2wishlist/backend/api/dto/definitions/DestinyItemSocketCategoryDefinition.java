package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemSocketCategoryDefinition {

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.Sockets.DestinySocketCategoryDefinition
     */
    @JsonProperty("socketCategoryHash")
    private Long socketCategoryHash;

    @JsonProperty("socketIndexes")
    private Integer[] socketIndexes;
}
