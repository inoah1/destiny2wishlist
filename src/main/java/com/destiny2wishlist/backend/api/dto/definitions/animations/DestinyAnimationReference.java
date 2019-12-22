package com.destiny2wishlist.backend.api.dto.definitions.animations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyAnimationReference {

    @JsonProperty("animName")
    private String animName;

    @JsonProperty("animIdentifier")
    private String animIdentifier;

    @JsonProperty("path")
    private String path;
}
