package com.destiny2wishlist.backend.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DestinyInventoryItemDefinition {

    @JsonProperty("displayProperties")
    private DestinyDisplayPropertiesDefinition displayProperties;

    @JsonProperty("inventory")
    private DestinyItemInventoryBlockDefinition inventory;

    @JsonProperty("itemCategoryHashes")
    private long[] itemCategoryHashes;

    @JsonProperty("itemType")
    private int itemType;

    @JsonProperty("itemSubType")
    private int itemSubType;

    @JsonProperty("equippable")
    private boolean equippable;

    @JsonProperty("sockets")
    private DestinyItemSocketBlockDefinition sockets;

    @JsonProperty("hash")
    private long hash;
}
