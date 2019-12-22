package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemActionBlockDefinition {

    @JsonProperty("verbName")
    private String verbName;

    @JsonProperty("verbDescription")
    private String verbDescription;

    @JsonProperty("isPositive")
    private Boolean isPositive;

    @JsonProperty("overlayScreenName")
    private String overlayScreenName;

    @JsonProperty("overlayIcon")
    private String overlayIcon;

    @JsonProperty("requiredCooldownSeconds")
    private Integer requiredCooldownSeconds;

    @JsonProperty("requiredItems")
    private DestinyItemActionRequiredItemDefinition[] requiredItems;

    @JsonProperty("progressionRewards")
    private DestinyProgressionRewardDefinition[] progressionRewards;

    @JsonProperty("actionTypeLabel")
    private String actionTypeLabel;

    @JsonProperty("rewardSheetHash")
    private Long rewardSheetHash;

    @JsonProperty("rewardItemHash")
    private Long rewardItemHash;

    @JsonProperty("rewardSiteHash")
    private Long rewardSiteHash;

    @JsonProperty("requiredLocation")
    private String requiredLocation;

    @JsonProperty("requiredCooldownHash")
    private Long requiredCooldownHash;

    @JsonProperty("deleteOnAction")
    private Boolean deleteOnAction;

    @JsonProperty("consumeEntireStack")
    private Boolean consumeEntireStack;

    @JsonProperty("useOnAcquire")
    private Boolean useOnAcquire;
}
