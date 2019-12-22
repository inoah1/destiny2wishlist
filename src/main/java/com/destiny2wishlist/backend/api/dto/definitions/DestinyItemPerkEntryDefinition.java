package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemPerkEntryDefinition {

    @JsonProperty("requirementDisplayString")
    private String requirementDisplayString;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinySandboxPerkDefinition
     */
    @JsonProperty("perkHash")
    private Long perkHash;

    @JsonProperty("perkVisibility")
    private Integer perkVisibility;
}
