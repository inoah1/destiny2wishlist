package com.destiny2wishlist.backend.api.dto.definitions.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Id;

@Data
public class DestinyItemQuantity {

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyInventoryItemDefinition
     */
    @Id
    @JsonProperty("itemHash")
    private Long itemHash;

    @JsonProperty("itemInstanceId")
    private Float itemInstanceId;

    @JsonProperty("quantity")
    private Integer quantity;
}
