package com.destiny2wishlist.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"weapon_hash", "barrel_hash", "magazine_hash", "first_perk_hash", "second_perk_hash"})})
public class DestinyWeaponRoll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private DestinyWeapon weapon;

    @ManyToOne
    private DestinyWeaponSocket barrel;

    @ManyToOne
    private DestinyWeaponSocket magazine;

    @ManyToOne
    private DestinyWeaponSocket firstPerk;

    @ManyToOne
    private DestinyWeaponSocket secondPerk;

    private String notes;

    public boolean isNewRoll() {
        return getId() == null || getId() == 0;
    }

    public String generateDimWishlistString() {
        final StringBuilder sb = new StringBuilder("dimwishlist:item=");
        sb.append(weapon.getHash());
        if (barrel != null || magazine != null || firstPerk != null || secondPerk != null) {
            sb.append("&perks=");
        }

        int perkAdded = 0;
        if (barrel != null) {
            sb.append(barrel.getHash());
            perkAdded++;
        }
        if (magazine != null) {
            if (perkAdded > 0) {
                sb.append(",");
            }
            sb.append(magazine.getHash());
            perkAdded++;
        }
        if (firstPerk != null) {
            if (perkAdded > 0) {
                sb.append(",");
            }
            sb.append(firstPerk.getHash());
            perkAdded++;
        }
        if (secondPerk != null) {
            if (perkAdded > 0) {
                sb.append(",");
            }
            sb.append(secondPerk.getHash());
            perkAdded++;
        }
        if (notes != null) {
            sb.append("#notes:");
            sb.append(notes);
        }
        return sb.toString();
    }

    public String getWeaponName() {
        return (weapon == null) ? "" : weapon.getName();
    }

    public String getMagazineName() {
        return (magazine == null) ? "" : magazine.getName();
    }

    public String getBarrelName() {
        return (barrel == null) ? "" : barrel.getName();
    }

    public String getFirstPerkName() {
        return (firstPerk == null) ? "" : firstPerk.getName();
    }

    public String getSecondPerkName() {
        return (secondPerk == null) ? "" : secondPerk.getName();
    }
}
