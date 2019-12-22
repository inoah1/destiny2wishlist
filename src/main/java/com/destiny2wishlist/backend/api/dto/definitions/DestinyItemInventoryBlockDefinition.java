package com.destiny2wishlist.backend.api.dto.definitions;


import com.destiny2wishlist.backend.api.dto.definitions.enums.DestinyTierTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemInventoryBlockDefinition {

    @JsonProperty("stackUniqueLabel")
    private String stackUniqueLabel;

    @JsonProperty("maxStackSize")
    private Integer maxStackSize;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyInventoryBucketDefinition
     */
    @JsonProperty("bucketTypeHash")
    private Long bucketTypeHash;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyInventoryBucketDefinition
     */
    @JsonProperty("recoveryBucketTypeHash")
    private Long recoveryBucketTypeHash;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.Items.DestinyItemTierTypeDefinition
     */
    @JsonProperty("tierTypeHash")
    private Long tierTypeHash;

    @JsonProperty("isInstanceItem")
    private Boolean isInstanceItem;

    @JsonProperty("nonTransferrableOriginal")
    private Boolean nonTransferrableOriginal;

    @JsonProperty("tierTypeName")
    private String tierTypeName;

    @JsonProperty("tierType")
    private DestinyTierTypeEnum tierType;

    @JsonProperty("expirationTooltip")
    private String expirationTooltip;

    @JsonProperty("expiredInActivityMessage")
    private String expiredInActivityMessage;

    @JsonProperty("expiredInOrbitMessage")
    private String expiredInOrbitMessage;

    @JsonProperty("suppressExpirationWhenObjectivesComplete")
    private Boolean suppressExpirationWhenObjectivesComplete;
}
