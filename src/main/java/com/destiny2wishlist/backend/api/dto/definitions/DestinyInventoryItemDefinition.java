package com.destiny2wishlist.backend.api.dto.definitions;


import com.destiny2wishlist.backend.api.dto.definitions.animations.DestinyAnimationReference;
import com.destiny2wishlist.backend.api.dto.definitions.common.DestinyDisplayPropertiesDefinition;
import com.destiny2wishlist.backend.api.dto.definitions.enums.*;
import com.destiny2wishlist.backend.api.dto.definitions.items.DestinyItemPlugDefinition;
import com.destiny2wishlist.backend.api.dto.definitions.utils.DestinyColor;
import com.destiny2wishlist.backend.api.dto.definitions.utils.HyperlinkReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DestinyInventoryItemDefinition extends ParentDefinition {

    @JsonProperty("displayProperties")
    private DestinyDisplayPropertiesDefinition displayProperty;

    @JsonProperty("tooltipNotifications")
    private DestinyItemTooltipNotification[] tooltipNotifications;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.Collectibles.DestinyCollectibleDefinition
     */
    @JsonProperty("collectibleHash")
    private Long collectibleHash;

    @JsonProperty("secondaryIcon")
    private String secondaryIcon;

    @JsonProperty("secondaryOverlay")
    private String secondaryOverlay;

    @JsonProperty("secondarySpecial")
    private String secondarySpecial;

    @JsonProperty("backgroundColor")
    private DestinyColor backgroundColor;

    @JsonProperty("screenshot")
    private String screenshot;

    @JsonProperty("itemTypeDisplayName")
    private String itemTypeDisplayName;

    @JsonProperty("uiItemDisplayStyle")
    private String uiItemDisplayStyle;

    @JsonProperty("itemTypeAndTierDisplayName")
    private String itemTypeAndTierDisplayName;

    @JsonProperty("displaySource")
    private String displaySource;

    @JsonProperty("tooltipStyle")
    private String tooltipStyle;

    @JsonProperty("action")
    private DestinyItemActionBlockDefinition action;

    @JsonProperty("inventory")
    private DestinyItemInventoryBlockDefinition inventory;

    @JsonProperty("setData")
    private DestinyItemSetBlockDefinition setData;

    @JsonProperty("stats")
    private DestinyItemStatBlockDefinition stats;

    @JsonProperty("emblemObjectiveHash")
    private Long emblemObjectiveHash;

    @JsonProperty("equippingBlock")
    private DestinyEquippingBlockDefinition equippingBlock;

    @JsonProperty("translationBlock")
    private DestinyItemTranslationBlockDefinition translationBlock;

    @JsonProperty("preview")
    private DestinyItemPreviewBlockDefinition preview;

    @JsonProperty("quality")
    private DestinyItemQualityBlockDefinition quality;

    @JsonProperty("acquireRewardSiteHash")
    private Long acquireRewardSiteHash;

    @JsonProperty("acquireUnlockHash")
    private Long acquireUnlockHash;

    @JsonProperty("value")
    private DestinyItemValueBlockDefinition value;

    @JsonProperty("sourceData")
    private DestinyItemSourceBlockDefinition sourceData;

    @JsonProperty("objectives")
    private DestinyItemObjectiveBlockDefinition objectives;

    @JsonProperty("plug")
    private DestinyItemPlugDefinition plug;

    @JsonProperty("gearset")
    private DestinyItemGearsetBlockDefinition gearset;

    @JsonProperty("sack")
    private DestinyItemSackBlockDefinition sack;

    @JsonProperty("sockets")
    private DestinyItemSocketBlockDefinition sockets;

    @JsonProperty("summary")
    private DestinyItemSummaryBlockDefinition summary;

    @JsonProperty("talentGrid")
    private DestinyItemTalentGridBlockDefinition talentGrid;

    @JsonProperty("investmentStats")
    private DestinyItemInvestmentStatDefinition[] investmentStats;

    @JsonProperty("perks")
    private DestinyItemPerkEntryDefinition[] perks;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.Lore.DestinyLoreDefinition
     */
    @JsonProperty("loreHash")
    private Long loreHash;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyInventoryItemDefinition
     */
    @JsonProperty("summaryItemHash")
    private Long summaryItemHash;

    @JsonProperty("animations")
    private DestinyAnimationReference[] animations;

    @JsonProperty("allowActions")
    private Boolean allowActions;

    @JsonProperty("links")
    private HyperlinkReference[] links;

    @JsonProperty("doesPostmasterPullHaveSideEffects")
    private Boolean doesPostmasterPullHaveSideEffects;

    @JsonProperty("nonTransferrable")
    private Boolean nonTransferrable;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyItemCategoryDefinition
     */
    @JsonProperty("itemCategoryHashes")
    private Long[] itemCategoryHashes;

    @JsonProperty("specialItemType")
    private DestinySpecialItemTypeEnum specialItemType;

    @JsonProperty("itemType")
    private DestinyItemTypeEnum itemType;

    @JsonProperty("itemSubType")
    private DestinyItemSubTypeEnum itemSubType;

    @JsonProperty("classType")
    private DestinyClassEnum classType;

    @JsonProperty("breakerType")
    private Integer breakerType;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.BreakerTypes.DestinyBreakerTypeDefinition
     */
    @JsonProperty("breakerTypeHash")
    private Long breakerTypeHash;

    @JsonProperty("equippable")
    private Boolean equippable;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.DestinyDamageTypeDefinition
     */
    @JsonProperty("damageTypeHashes")
    private Long[] damageTypeHash;

    @JsonProperty("damageTypes")
    private Integer[] damageTypes;

    @JsonProperty("defaultDamageType")
    private DestinyDamageTypeEnum defaultDamageType;

    @JsonProperty("defaultDamageTypeHash")
    private Long defaultDamageTypeHash;

    /**
     * Mapped to Manifest Database Definition: Destiny.Definitions.Seasons.DestinySeasonDefinition
     */
    @JsonProperty("seasonHash")
    private Long seasonHash;

    @JsonProperty("isWrapper")
    private Boolean isWrapper;
}
