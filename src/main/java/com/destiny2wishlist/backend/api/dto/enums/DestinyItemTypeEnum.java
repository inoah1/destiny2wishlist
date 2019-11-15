package com.destiny2wishlist.backend.api.dto.enums;

import lombok.Getter;

@Getter
public enum DestinyItemTypeEnum {
    WEAPON(3);

    private int value;

    DestinyItemTypeEnum(int i) {
        this.value = i;
    }
}
