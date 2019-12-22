package com.destiny2wishlist.backend.api.dto.definitions.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyEnergyCostEntry {

    @JsonProperty("energyCost")
    private Integer energyCost;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.EnergyTypes.DestinyEnergyTypeDefinition
     */
    @JsonProperty("energyTypeHash")
    private Long energyTypeHash;

    @JsonProperty("energyType")
    private Integer energyType;
}
