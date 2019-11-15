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
public class DestinyItemInventoryBlockDefinition {

    @JsonProperty("stackUniqueLabel")
    private String stackUniqueLabel;

    @JsonProperty("maxStackSize")
    private int maxStackSize;

    @JsonProperty("bucketTypeHash")
    private long bucketTypeHash;

    @JsonProperty("recoveryBucketTypeHash")
    private long recoveryBucketTypeHash;

    @JsonProperty("tierTypeHash")
    private long tierTypeHash;

    @JsonProperty("isInstanceItem")
    private boolean isInstanceItem;

    @JsonProperty("tierTypeName")
    private String tierTypeName;

    @JsonProperty("tierType")
    private int tierType;

    @JsonProperty("expirationTooltip")
    private String expirationTooltip;

    @JsonProperty("expiredInActivityMessage")
    private String expiredInActivityMessage;

    @JsonProperty("expiredInOrbitMessage")
    private String expiredInOrbitMessage;

    @JsonProperty("suppressExpirationWhenObjectivesComplete")
    private boolean suppressExpirationWhenObjectivesComplete;
}
