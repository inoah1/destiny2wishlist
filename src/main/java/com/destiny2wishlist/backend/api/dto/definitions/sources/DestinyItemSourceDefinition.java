package com.destiny2wishlist.backend.api.dto.definitions.sources;


import com.destiny2wishlist.backend.api.dto.definitions.DestinyInventoryItemStatDefinition;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class DestinyItemSourceDefinition {

    @JsonProperty("level")
    private Integer level;

    @JsonProperty("minQuality")
    private Integer minQuality;

    @JsonProperty("maxQuality")
    private Integer maxQuality;

    @JsonProperty("minLevelRequired")
    private Integer minLevelRequired;

    @JsonProperty("maxLevelRequired")
    private Integer maxLevelRequired;

    @JsonProperty("computedStats")
    private Map<Long, DestinyInventoryItemStatDefinition> computedStats;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyRewardSourceDefinition
     */
    @JsonProperty("sourceHashes")
    private Long[] sourceHashes;
}
