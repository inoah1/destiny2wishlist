package com.destiny2wishlist.backend.api.dto.definitions;

import com.destiny2wishlist.backend.api.dto.definitions.common.DestinyDisplayPropertiesDefinition;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DestinyItemCategoryDefinition extends ParentDefinition {

    @JsonProperty("displayProperties")
    private DestinyDisplayPropertiesDefinition displayProperty;

    @JsonProperty("visible")
    private Boolean visible;

    @JsonProperty("deprecated")
    private Boolean deprecated;

    @JsonProperty("shortTitle")
    private String shortTitle;

    @JsonProperty("itemTypeRegex")
    private String itemTypeRegex;

    @JsonProperty("grantDestinyBreakerType")
    private Integer grantDestinyBreakerType;

    @JsonProperty("plugCategoryIdentifier")
    private String plugCategoryIdentifier;

    @JsonProperty("itemTypeRegexNot")
    private String itemTypeRegexNot;

    @JsonProperty("originBucketIdentifier")
    private String originBucketIdentifier;

    @JsonProperty("grantDestinyItemType")
    private Integer grantDestinyItemType;

    @JsonProperty("grantDestinySubType")
    private Integer grantDestinySubType;

    @JsonProperty("grantDestinyClass")
    private Integer grantDestinyClass;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyItemCategoryDefinition
     */
    @JsonProperty("groupedCategoryHashes")
    private Long[] groupedCategoryHash;

    @JsonProperty("isPlug")
    private Boolean isPlug;

    @JsonProperty("parentCategoryHashes")
    private Long[] parentCategoryHash;

    @JsonProperty("groupCategoryOnly")
    private Boolean groupCategoryOnly;
}
