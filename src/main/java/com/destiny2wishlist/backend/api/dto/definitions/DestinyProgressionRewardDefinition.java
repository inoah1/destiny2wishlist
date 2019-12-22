package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyProgressionRewardDefinition {

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyProgressionMappingDefinition
     */
    @JsonProperty("progressionMappingHash")
    private Long progressionMappingHash;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("applyThrottles")
    private Boolean applyThrottles;
}
