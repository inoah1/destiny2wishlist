package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemSummaryBlockDefinition {

    @JsonProperty("sortPriority")
    private Integer sortPriority;
}
