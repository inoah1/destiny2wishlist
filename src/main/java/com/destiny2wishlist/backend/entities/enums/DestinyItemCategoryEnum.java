package com.destiny2wishlist.backend.entities.enums;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public enum DestinyItemCategoryEnum {
    WEAPON(1),
    KINETIC_WEAPON(2),
    ENERGY_WEAPON(3),
    POWER_WEAPONN(4),
    AUTO_RIFLE(5),
    SUBMACHINE_GUNS(3954685534L);

    @Id
    private long hash;

    DestinyItemCategoryEnum(long hash) {
        this.hash = hash;
    }

    public static DestinyItemCategoryEnum fromLong(long hash) {
        for (DestinyItemCategoryEnum itemCategoryEnum : DestinyItemCategoryEnum.values()) {
            if (itemCategoryEnum.hash == hash) {
                return itemCategoryEnum;
            }
        }
        return null;
    }
}
