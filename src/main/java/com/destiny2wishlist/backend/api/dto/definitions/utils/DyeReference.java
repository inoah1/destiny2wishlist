package com.destiny2wishlist.backend.api.dto.definitions.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DyeReference {

    @JsonProperty("channelHash")
    private Long channelHash;

    @JsonProperty("dyeHash")
    private Long dyeHash;
}
