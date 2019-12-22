package com.destiny2wishlist.backend.api.dto.definitions.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DestinyItemTypeEnum {
    NONE(0),
    CURRENCY(1),
    ARMOR(2),
    WEAPON(3),
    MESSAGE(7),
    ENGRAM(8),
    CONSUMABLE(9),
    EXCHANGE_MATERIAL(10),
    MISSION_REWARD(11),
    QUEST_STEP(12),
    QUEST_STEP_COMPLETE(13),
    EMBLEM(14),
    QUEST(15),
    SUBCLASS(16),
    CLAN_BANNER(17),
    AURA(18),
    MOD(19),
    DUMMY(20),
    SHIP(21),
    VEHICLE(22),
    EMOTE(23),
    GHOST(24),
    PACKAGE(25),
    BOUNTY(26),
    WRAPPER(27),
    SEASONAL_ARTIFACT(28),
    FINISHER(29);

    private final int code;

    DestinyItemTypeEnum(int code) {
        this.code = code;
    }

    @JsonCreator
    public static DestinyItemTypeEnum getEnum(int code) {
        for (DestinyItemTypeEnum element : values()) {
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
