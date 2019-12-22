package com.destiny2wishlist.backend.api.dto.definitions.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DestinyItemSubTypeEnum {
    NONE(0),
    CRUCIBLE(1),
    VANGUARD(2),
    EXOTIC(5),
    AUTO_RIFLE(6),
    SHOTGUN(7),
    MACHINE_GUN(8),
    HAND_CANNON(9),
    ROCKET_LAUNCHER(10),
    FUSION_RIFLE(11),
    SNIPER_RIFLE(12),
    PULSE_RIFLE(13),
    SCOUT_RIFLE(14),
    CRM(16),
    SIDEARM(17),
    SWORD(18),
    MASK(19),
    SHADER(20),
    ORNAMENT(21),
    FUSION_RIFLE_LINE(22),
    GRENADE_LAUNCHER(23),
    SUB_MACHINE_GUN(24),
    TRACE_RIFLE(25),
    HELMET_ARMOR(26),
    GAUNTLETS_ARMOR(27),
    CHEST_ARMOR(28),
    LEG_ARMOR(29),
    CLASS_ARMOR(30),
    BOW(31);

    private final int code;

    DestinyItemSubTypeEnum(int code) {
        this.code = code;
    }

    @JsonCreator
    public static DestinyItemSubTypeEnum getEnum(int code) {
        for (DestinyItemSubTypeEnum element : values()) {
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
