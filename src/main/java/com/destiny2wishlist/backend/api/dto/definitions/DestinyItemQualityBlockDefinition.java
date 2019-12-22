package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemQualityBlockDefinition {

    @JsonProperty("itemLevels")
    private Integer[] itemLevels;

    @JsonProperty("qualityLevel")
    private Integer qualityLevel;

    @JsonProperty("infusionCategoryName")
    private String infusionCategoryName;

    @JsonProperty("infusionCategoryHash")
    private Long infusionCategoryHash;

    @JsonProperty("infusionCategoryHashes")
    private Long[] infusionCategoryHashes;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.Progression.DestinyProgressionLevelRequirementDefinition
     */
    @JsonProperty("progressionLevelRequirementHash")
    private Long progressionLevelRequirementHash;
}
