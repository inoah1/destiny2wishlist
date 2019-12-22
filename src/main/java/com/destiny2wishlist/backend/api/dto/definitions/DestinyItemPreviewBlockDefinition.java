package com.destiny2wishlist.backend.api.dto.definitions;


import com.destiny2wishlist.backend.api.dto.definitions.items.DestinyDerivedItemCategoryDefinition;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemPreviewBlockDefinition {

    @JsonProperty("screenStyle")
    private String screenStyle;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyVendorDefinition
     */
    @JsonProperty("previewVendorHash")
    private Long previewVendorHash;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.Artifacts.DestinyArtifactDefinition
     */
    @JsonProperty("artifactHash")
    private Long artifactHash;

    @JsonProperty("previewActionString")
    private String previewActionString;

    @JsonProperty("derivedItemCategories")
    private DestinyDerivedItemCategoryDefinition[] derivedItemCategories;
}
