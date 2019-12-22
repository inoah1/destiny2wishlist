package com.destiny2wishlist.backend.api.dto.definitions.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DestinySpecialItemTypeEnum {

    NONE(0),
    SPECIAL_CURRENCY(1),
    ARMOR(8),
    WEAPON(9),
    ENGRAM(23),
    CONSUMABLE(24),
    EXCHANGE_MATERIAL(25),
    MISSION_REWARD(27),
    CURRENCY(29);

    private final int code;

    DestinySpecialItemTypeEnum(int code) {
        this.code = code;
    }

    @JsonCreator
    public static DestinySpecialItemTypeEnum getEnum(int code) {
        for (DestinySpecialItemTypeEnum element : values()) {
            if (element.code == code) {
                return element;
            }
        }
        return null;
    }

    @JsonValue
    public int jsonValue() {
        return this.code;
    }
}
