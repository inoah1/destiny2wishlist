package com.destiny2wishlist.backend.api.dto.definitions.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HyperlinkReference {

    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;
}
