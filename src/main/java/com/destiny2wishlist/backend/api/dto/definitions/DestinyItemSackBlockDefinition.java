package com.destiny2wishlist.backend.api.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DestinyItemSackBlockDefinition {

    @JsonProperty("detailAction")
    private String detailAction;

    @JsonProperty("openAction")
    private String openAction;

    @JsonProperty("seedUnlockValueHash")
    private Long seedUnlockValueHash;

    @JsonProperty("resolvedBitVectorUnlockValueHash")
    private Long resolvedBitVectorUnlockValueHash;

    @JsonProperty("resolvedItemCountUnlockValueHash")
    private Long resolvedItemCountUnlockValueHash;

    @JsonProperty("selectItemCount")
    private Integer selectItemCount;

    @JsonProperty("rollStateUnlockValueHash")
    private Long rollStateUnlockValueHash;

    @JsonProperty("rewardItemListHash")
    private Long rewardItemListHash;

    @JsonProperty("vendorSackType")
    private String vendorSackType;

    @JsonProperty("openOnAcquire")
    private Boolean openOnAcquire;
}
