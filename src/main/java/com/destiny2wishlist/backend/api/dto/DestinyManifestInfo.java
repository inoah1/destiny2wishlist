package com.destiny2wishlist.backend.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DestinyManifestInfo {

    @JsonProperty("version")
    private String version;

    @JsonProperty("jsonWorldContentPaths")
    private ContentPathInfo jsonWorldContentPaths;
}
