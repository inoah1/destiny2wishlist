package com.destiny2wishlist.backend.api.dto.definitions.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DestinyTierTypeEnum {

    UNKNOWN(0),
    CURRENCY(1),
    BASIC(2),
    COMMON(3),
    RARE(4),
    SUPERIOR(5),
    EXOTIC(6);

    private final int code;

    DestinyTierTypeEnum(int code) {
        this.code = code;
    }

    @JsonCreator
    public static DestinyTierTypeEnum getEnum(int code) {
        for (DestinyTierTypeEnum element : values()) {
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
