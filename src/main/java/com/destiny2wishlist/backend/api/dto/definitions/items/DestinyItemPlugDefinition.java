package com.destiny2wishlist.backend.api.dto.definitions.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemPlugDefinition {

    @JsonProperty("insertionRules")
    private DestinyPlugRuleDefinition[] insertionRules;

    @JsonProperty("plugCategoryIdentifier")
    private String plugCategoryIdentifier;

    @JsonProperty("plugCategoryHash")
    private Long plugCategoryHash;

    @JsonProperty("onActionRecreateSelf")
    private Boolean onActionRecreateSelf;

    @JsonProperty("actionRewardSiteHash")
    private Long actionRewardSiteHash;

    @JsonProperty("actionRewardItemOverrideHash")
    private Long actionRewardItemOverrideHash;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyMaterialRequirementSetDefinition
     */
    @JsonProperty("insertionMaterialRequirementHash")
    private Long insertionMaterialRequirementHash;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyInventoryItemDefinition
     */
    @JsonProperty("previewItemOverrideHash")
    private Long previewItemOverrideHash;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyMaterialRequirementSetDefinition
     */
    @JsonProperty("enabledMaterialRequirementHash")
    private Long enabledMaterialRequirementHash;

    @JsonProperty("enabledRules")
    private DestinyPlugRuleDefinition[] enabledRules;

    @JsonProperty("uiPlugLabel")
    private String uiPlugLabel;

    @JsonProperty("plugStyle")
    private Integer plugStyle;

    @JsonProperty("plugAvailability")
    private Integer plugAvailability;

    @JsonProperty("alternateUiPlugLabel")
    private String alternateUiPlugLabel;

    @JsonProperty("alternatePlugStyle")
    private Integer alternatePlugStyle;

    @JsonProperty("isDummyPlug")
    private Boolean isDummyPlug;

    @JsonProperty("parentItemOverride")
    private DestinyParentItemOverride parentItemOverride;

    @JsonProperty("energyCapacity")
    private DestinyEnergyCapacityEntry energyCapacity;

    @JsonProperty("energyCost")
    private DestinyEnergyCostEntry energyCost;
}
