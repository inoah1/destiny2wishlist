package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyObjectiveDisplayProperties {

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyActivityDefinition
     */
    @JsonProperty("activityHash")
    private Long activityHash;

    @JsonProperty("displayOnItemPreviewScreen")
    private Boolean displayOnItemPreviewScreen;
}
