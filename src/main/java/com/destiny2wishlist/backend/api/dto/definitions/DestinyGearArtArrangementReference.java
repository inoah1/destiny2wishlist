package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyGearArtArrangementReference {

    @JsonProperty("classHash")
    private Long classHash;

    @JsonProperty("artArrangementHash")
    private Long artArrangementHash;
}
