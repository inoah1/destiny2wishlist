package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemSocketEntryDefinition {

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.Sockets.DestinySocketTypeDefinition
     */
    @JsonProperty("socketTypeHash")
    private Long socketTypeHash;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyInventoryItemDefinition
     */
    @JsonProperty("singleInitialItemHash")
    private Long singleInitialItemHash;

    @JsonProperty("reusablePlugItems")
    private DestinyItemSocketEntryPlugItemDefinition[] reusablePlugItems;

    @JsonProperty("preventInitializationOnVendorPurchase")
    private Boolean preventInitializationOnVendorPurchase;

    @JsonProperty("preventInitializationWhenVersioning")
    private Boolean preventInitializationWhenVersioning;

    @JsonProperty("hidePerksInItemTooltip")
    private Boolean hidePerksInItemTooltip;

    @JsonProperty("plugSources")
    private Integer plugSources;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.Sockets.DestinyPlugSetDefinition
     */
    @JsonProperty("reusablePlugSetHash")
    private Long reusablePlugSetHash;

    @JsonProperty("overridesUiAppearance")
    private Boolean overridesUiAppearance;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.Sockets.DestinyPlugSetDefinition
     */
    @JsonProperty("randomizedPlugSetHash")
    private Long randomizedPlugSetHash;

    @JsonProperty("defaultVisible")
    private Boolean defaultVisible;
}
