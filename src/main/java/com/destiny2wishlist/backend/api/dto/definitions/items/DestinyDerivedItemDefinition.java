package com.destiny2wishlist.backend.api.dto.definitions.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyDerivedItemDefinition {

    @JsonProperty("itemHash")
    private Long itemHash;

    @JsonProperty("itemName")
    private String itemName;

    @JsonProperty("itemDetail")
    private String itemDetail;

    @JsonProperty("itemDescription")
    private String itemDescription;

    @JsonProperty("iconPath")
    private String iconPath;

    @JsonProperty("vendorItemIndex")
    private Integer vendorItemIndex;
}
