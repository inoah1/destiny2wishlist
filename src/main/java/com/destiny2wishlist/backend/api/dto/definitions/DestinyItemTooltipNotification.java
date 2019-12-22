package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemTooltipNotification {

    @JsonProperty("displayString")
    private String displayString;

    @JsonProperty("displayStyle")
    private String displayStyle;
}
