package com.destiny2wishlist.backend.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashMap;

@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DestinyManifestJson {

    @JsonProperty("DestinyInventoryItemDefinition")
    private LinkedHashMap<String, DestinyInventoryItemDefinition> inventoryItemDefinitions;

    @JsonIgnore
    private String jsonData;
}
