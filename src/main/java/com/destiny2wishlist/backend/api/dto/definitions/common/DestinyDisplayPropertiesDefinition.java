package com.destiny2wishlist.backend.api.dto.definitions.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.List;

@Data
public class DestinyDisplayPropertiesDefinition {

    @JsonProperty("description")
    @Column(length = 510)
    private String description;

    @JsonProperty("name")
    private String name;

    @JsonProperty("icon")
    private String icon;

    @JsonProperty("highResIcon")
    private String highResIcon;

    @JsonProperty("hasIcon")
    private boolean hasIcon;

    @JsonProperty("subtitle")
    private String subtitle;

    @JsonProperty("originalIcon")
    private String originalIcon;

    @JsonProperty("requirementsDisplay")
    @ElementCollection
    private List<String> requirementsDisplay;
}
