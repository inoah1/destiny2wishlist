package com.destiny2wishlist.backend.api.dto.definitions.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyColor {

    @JsonProperty("red")
    private Byte red;

    @JsonProperty("green")
    private Byte green;

    @JsonProperty("blue")
    private Byte blue;

    @JsonProperty("alpha")
    private Byte alpha;

    @JsonProperty("colorHash")
    private Long colorHash;
}
