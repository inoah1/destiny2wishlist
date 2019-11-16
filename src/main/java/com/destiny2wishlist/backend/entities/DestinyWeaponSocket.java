package com.destiny2wishlist.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "destiny_weapon_socket")
@NoArgsConstructor
@AllArgsConstructor
public class DestinyWeaponSocket extends AbstractEntity {

    private String name;

    private String description;

    private String icon;
}
