package com.destiny2wishlist.backend.api.dto.definitions.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyEnergyCapacityEntry {

    @JsonProperty("capacityValue")
    private Integer capacityValue;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.EnergyTypes.DestinyEnergyTypeDefinition
     */
    @JsonProperty("energyTypeHash")
    private Long energyTypeHash;

    @JsonProperty("energyType")
    private Integer energyType;
}
