package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemObjectiveBlockDefinition {

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyObjectiveDefinition
     */
    @JsonProperty("objectiveHashes")
    private Long[] objectiveHashes;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyActivityDefinition
     */
    @JsonProperty("displayActivityHashes")
    private Long[] displayActivityHashes;

    @JsonProperty("requireFullObjectiveCompletion")
    private Boolean requireFullObjectiveCompletion;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyInventoryItemDefinition
     */
    @JsonProperty("questlineItemHash")
    private Long questlineItemHash;

    @JsonProperty("narrative")
    private String narrative;

    @JsonProperty("objectiveVerbName")
    private String objectiveVerbName;

    @JsonProperty("questTypeIdentifier")
    private String questTypeIdentifier;

    @JsonProperty("questTypeHash")
    private Long questTypeHash;

    @JsonProperty("completionRewardSiteHash")
    private Long completionRewardSiteHash;

    @JsonProperty("nextQuestStepRewardSiteHash")
    private Long nextQuestStepRewardSiteHash;

    @JsonProperty("timestampUnlockValueHash")
    private Long timestampUnlockValueHash;

    @JsonProperty("isGlobalObjectiveItem")
    private Boolean isGlobalObjectiveItem;

    @JsonProperty("useOnObjectiveCompletion")
    private Boolean useOnObjectiveCompletion;

    @JsonProperty("inhibitCompletionUnlockValueHash")
    private Long inhibitCompletionUnlockValueHash;

    @JsonProperty("perObjectiveDisplayProperties")
    private DestinyObjectiveDisplayProperties[] perObjectiveDisplayProperties;
}
