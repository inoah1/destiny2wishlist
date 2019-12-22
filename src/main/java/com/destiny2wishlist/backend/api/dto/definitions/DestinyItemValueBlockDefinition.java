package com.destiny2wishlist.backend.api.dto.definitions;


import com.destiny2wishlist.backend.api.dto.definitions.utils.DestinyItemQuantity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemValueBlockDefinition {

    @JsonProperty("itemValue")
    private DestinyItemQuantity[] itemValue;

    @JsonProperty("valueDescription")
    private String valueDescription;
}
