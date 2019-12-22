package com.destiny2wishlist.backend.api.dto.definitions.sockets;

import com.destiny2wishlist.backend.api.dto.definitions.DestinyItemSocketEntryPlugItemRandomizedDefinition;
import com.destiny2wishlist.backend.api.dto.definitions.ParentDefinition;
import com.destiny2wishlist.backend.api.dto.definitions.common.DestinyDisplayPropertiesDefinition;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DestinyPlugSetDefinition extends ParentDefinition {

    @JsonProperty("displayProperties")
    private DestinyDisplayPropertiesDefinition displayProperty;

    @JsonProperty("reusablePlugItems")
    private DestinyItemSocketEntryPlugItemRandomizedDefinition[] reusablePlugItems;

    @JsonProperty("isFakePlugSet")
    private Boolean isFakePlugSet;
}
