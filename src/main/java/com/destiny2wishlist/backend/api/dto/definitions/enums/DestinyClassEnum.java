package com.destiny2wishlist.backend.api.dto.definitions.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DestinyClassEnum {

    TITAN(0),
    HUNTER(1),
    WARLOCK(2),
    UNKNOWN(3);

    private final int code;

    DestinyClassEnum(int code) {
        this.code = code;
    }

    @JsonCreator
    public static DestinyClassEnum getEnum(int code) {
        for (DestinyClassEnum element : values()) {
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
