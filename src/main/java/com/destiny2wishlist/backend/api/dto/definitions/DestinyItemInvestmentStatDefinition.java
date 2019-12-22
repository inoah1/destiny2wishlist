package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemInvestmentStatDefinition {

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyStatDefinition
     */
    @JsonProperty("statTypeHash")
    private Long statTypeHash;

    @JsonProperty("value")
    private Integer value;

    @JsonProperty("isConditionallyActive")
    private Boolean isConditionallyActive;
}
