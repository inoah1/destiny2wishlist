package com.destiny2wishlist.backend.entities;

import com.destiny2wishlist.backend.api.dto.definitions.enums.DestinyItemSubTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class DestinyWeapon extends AbstractEntity {

    private String name;

    private String description;

    private String icon;

    @Enumerated(EnumType.ORDINAL)
    private DestinyItemSubTypeEnum itemSubType;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<DestinyWeaponSocket> intrinsicPerk;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<DestinyWeaponSocket> barrel;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<DestinyWeaponSocket> magazine;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<DestinyWeaponSocket> firstPerk;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<DestinyWeaponSocket> secondPerk;

}
