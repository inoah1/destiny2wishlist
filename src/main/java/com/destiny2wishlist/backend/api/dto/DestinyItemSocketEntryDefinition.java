package com.destiny2wishlist.backend.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DestinyItemSocketEntryDefinition {

    @JsonProperty("socketTypeHash")
    private long socketTypeHash;

    @JsonProperty("singleInitialItemHash")
    private long singleInitialItemHash;

    @JsonProperty("plugSources")
    private int plugSources;

    @JsonProperty("reusablePlugSetHash")
    private long reusablePlugSetHash;

    @JsonProperty("randomizedPlugSetHash")
    private long randomizedPlugSetHash;

    @JsonProperty("defaultVisible")
    private boolean defaultVisible;
}
