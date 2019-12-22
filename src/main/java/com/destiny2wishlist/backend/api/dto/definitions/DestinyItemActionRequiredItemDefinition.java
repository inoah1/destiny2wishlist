package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemActionRequiredItemDefinition {

    @JsonProperty("count")
    private Integer count;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyInventoryItemDefinition
     */
    @JsonProperty("itemHash")
    private Long itemHash;

    @JsonProperty("deleteOnAction")
    private Boolean deleteOnAction;
}
