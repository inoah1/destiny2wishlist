package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

@Data
public class ParentDefinition implements Serializable {

    @Id
    @JsonProperty("hash")
    private Long hash;

    @JsonProperty("index")
    private Integer indexDefinition;

    @JsonProperty("redacted")
    private Boolean redacted;

    @JsonProperty("blacklisted")
    private Boolean blacklisted;
}
