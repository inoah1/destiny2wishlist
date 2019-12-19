package com.destiny2wishlist.backend.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "destiny_weapon")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DestinyWeapon extends AbstractEntity {

    private String name;

    private String description;

    private String icon;

    private int itemType;

    private int itemSubType;

    private long[] itemCategory;

}
