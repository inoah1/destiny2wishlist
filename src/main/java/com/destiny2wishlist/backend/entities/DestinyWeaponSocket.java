package com.destiny2wishlist.backend.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class DestinyWeaponSocket extends AbstractEntity {

    private String name;

    private String type;
}
