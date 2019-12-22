package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemVendorSourceReference {

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyVendorDefinition
     */
    @JsonProperty("vendorHash")
    private Long vendorHash;

    @JsonProperty("vendorItemIndexes")
    private Integer[] vendorItemIndexes;
}
