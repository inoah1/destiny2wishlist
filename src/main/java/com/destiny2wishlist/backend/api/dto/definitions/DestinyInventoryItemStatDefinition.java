package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyInventoryItemStatDefinition {

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyStatDefinition
     */
    @JsonProperty("statHash")
    private Long statHash;

    @JsonProperty("value")
    private Integer value;

    @JsonProperty("minimum")
    private Integer minimum;

    @JsonProperty("maximum")
    private Integer maximum;
}
