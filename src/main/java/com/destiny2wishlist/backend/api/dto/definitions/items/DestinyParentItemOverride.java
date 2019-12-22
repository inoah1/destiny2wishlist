package com.destiny2wishlist.backend.api.dto.definitions.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyParentItemOverride {

    @JsonProperty("additionalEquipRequirementsDisplayStrings")
    private String[] additionalEquipRequirementsDisplayStrings;

    @JsonProperty("pipIcon")
    private String pipIcon;
}
