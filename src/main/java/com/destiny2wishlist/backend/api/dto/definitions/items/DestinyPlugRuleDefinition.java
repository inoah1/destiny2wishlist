package com.destiny2wishlist.backend.api.dto.definitions.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyPlugRuleDefinition {

    @JsonProperty("failureMessage")
    private String failureMessage;
}
