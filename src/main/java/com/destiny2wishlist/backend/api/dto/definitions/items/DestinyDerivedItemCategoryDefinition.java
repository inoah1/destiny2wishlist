package com.destiny2wishlist.backend.api.dto.definitions.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyDerivedItemCategoryDefinition {

    @JsonProperty("categoryDescription")
    private String categoryDescription;

    @JsonProperty("items")
    private DestinyDerivedItemDefinition[] items;

    @JsonProperty("categoryIndex")
    private Integer categoryIndex;
}
