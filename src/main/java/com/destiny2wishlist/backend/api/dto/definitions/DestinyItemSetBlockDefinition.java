package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemSetBlockDefinition {

    @JsonProperty("itemList")
    private DestinyItemSetBlockEntryDefinition[] itemList;

    @JsonProperty("trackingUnlockValueHash")
    private Long trackingUnlockValueHash;

    @JsonProperty("abandonmentUnlockHash")
    private Long abandonmentUnlockHash;

    @JsonProperty("requireOrderedSetItemAdd")
    private Boolean requireOrderedSetItemAdd;

    @JsonProperty("setIsFeatured")
    private Boolean setIsFeatured;

    @JsonProperty("setType")
    private String setType;

    @JsonProperty("questLineName")
    private String questLineName;
}
