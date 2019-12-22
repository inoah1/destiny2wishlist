package com.destiny2wishlist.backend.api.dto.definitions;

import com.destiny2wishlist.backend.api.dto.definitions.sources.DestinyItemSourceDefinition;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemSourceBlockDefinition {

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyRewardSourceDefinition
     */
    @JsonProperty("sourceHashes")
    private Long[] sourceHashes;

    @JsonProperty("sources")
    private DestinyItemSourceDefinition[] sources;

    @JsonProperty("exclusive")
    private Integer exclusive;

    @JsonProperty("vendorSources")
    private DestinyItemVendorSourceReference[] vendorSources;
}
