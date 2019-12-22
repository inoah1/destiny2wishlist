package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class DestinyItemStatBlockDefinition {

    @JsonProperty("disablePrimaryStatDisplay")
    private Boolean disablePrimaryStatDisplay;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyStatGroupDefinition
     */
    @JsonProperty("statGroupHash")
    private Long statGroupHash;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyStatDefinition
     */
    @JsonProperty("stats")
    private Map<Long, DestinyInventoryItemStatDefinition> stats;

    @JsonProperty("hasDisplayableStats")
    private Boolean hasDisplayableStats;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyStatDefinition
     */
    @JsonProperty("primaryBaseStatHash")
    private Long primaryBaseStatHash;
}
