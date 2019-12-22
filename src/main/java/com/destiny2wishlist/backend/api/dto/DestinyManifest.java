package com.destiny2wishlist.backend.api.dto;

import com.destiny2wishlist.backend.api.dto.definitions.DestinyInventoryItemDefinition;
import com.destiny2wishlist.backend.api.dto.definitions.DestinyItemCategoryDefinition;
import com.destiny2wishlist.backend.api.dto.definitions.sockets.DestinyPlugSetDefinition;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.LinkedHashMap;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DestinyManifest {

    @JsonProperty("DestinyInventoryItemDefinition")
    private LinkedHashMap<Long, DestinyInventoryItemDefinition> destinyInventoryItemDefinition;

    @JsonProperty("DestinyItemCategoryDefinition")
    private LinkedHashMap<Long, DestinyItemCategoryDefinition> destinyItemCategoryDefinition;

    @JsonProperty("DestinyPlugSetDefinition")
    private LinkedHashMap<Long, DestinyPlugSetDefinition> destinyPlugSetDefinition;
}
