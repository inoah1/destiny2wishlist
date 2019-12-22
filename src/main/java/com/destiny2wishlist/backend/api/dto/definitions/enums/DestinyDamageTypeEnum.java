package com.destiny2wishlist.backend.api.dto.definitions.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DestinyDamageTypeEnum {

    NONE(0),
    KINETIC(1),
    ARC(2),
    THERMAL(3),
    VOID(4),
    RAID(5);

    private final int code;

    DestinyDamageTypeEnum(int code) {
        this.code = code;
    }

    @JsonCreator
    public static DestinyDamageTypeEnum getEnum(int code) {
        for (DestinyDamageTypeEnum element : values()) {
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
