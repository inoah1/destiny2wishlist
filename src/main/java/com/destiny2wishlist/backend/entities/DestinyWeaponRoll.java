package com.destiny2wishlist.backend.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class DestinyWeaponRoll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String weaponName;

    private String weaponId;

    private String firstPerkName;

    private String firstPerkId;

    private String secondPerkName;

    private String secondPerkId;

    private String thirdPerkName;

    private String thirdPerkId;

    private String fourthPerkName;

    private String fourthPerkId;

    public boolean isNewRoll() {
        return getId() == null || getId() == 0;
    }
}
