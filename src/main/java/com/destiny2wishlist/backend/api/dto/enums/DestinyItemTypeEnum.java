package com.destiny2wishlist.backend.api.dto.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum DestinyItemTypeEnum {
    WEAPON(3),
    WEAPON_MOD_INTRINSIC(2237038328L),
    WEAPON_MOD_MAGAZINE(4184407433L),
    WEAPON_MOD_SIGHTS(3866509906L),
    WEAPON_MOD_FRAME(3708671066L);

    private final long value;

    DestinyItemTypeEnum(long i) {
        this.value = i;
    }
}
