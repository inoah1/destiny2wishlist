package com.destiny2wishlist.backend.entities;

import com.destiny2wishlist.backend.api.dto.enums.DestinyItemTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name="destiny_weapon")
@NoArgsConstructor
@AllArgsConstructor
public class DestinyWeapon extends AbstractEntity {

    private String name;

    private String description;

    private String icon;

    private int itemType;

    private int itemSubType;

    @ElementCollection(targetClass = DestinyItemTypeEnum.class)
    @CollectionTable(name = "weapon_item_categories")
    @Column(name = "itemCategory")
    private Collection<DestinyItemTypeEnum> itemCategory;
}
