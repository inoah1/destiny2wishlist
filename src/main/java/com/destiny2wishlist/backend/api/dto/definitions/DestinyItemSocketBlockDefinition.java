package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemSocketBlockDefinition {

    @JsonProperty("detail")
    private String detail;

    @JsonProperty("socketEntries")
    private DestinyItemSocketEntryDefinition[] socketEntries;

    @JsonProperty("intrinsicSockets")
    private DestinyItemIntrinsicSocketEntryDefinition[] intrinsicSockets;

    @JsonProperty("socketCategories")
    private DestinyItemSocketCategoryDefinition[] socketCategories;
}
