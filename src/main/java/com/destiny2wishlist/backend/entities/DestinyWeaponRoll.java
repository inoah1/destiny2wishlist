package com.destiny2wishlist.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"weaponId", "barrelId", "magazineId", "firstPerkId", "secondPerkId"})})
public class DestinyWeaponRoll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String weaponName;

    @Column(nullable = false)
    private Long weaponId;

    private String barrel;

    private Long barrelId;

    private String magazine;

    private Long magazineId;

    private String firstPerkName;

    private Long firstPerkId;

    private String secondPerkName;

    private Long secondPerkId;

    private String notes;

    public boolean isNewRoll() {
        return getId() == null || getId() == 0;
    }

    public String generateDimWishlistString() {
        final StringBuilder sb = new StringBuilder("dimwishlist:item=");
        sb.append(weaponId);
        if (barrelId != null || magazineId != null || firstPerkId != null || secondPerkId != null) {
            sb.append("&perks=");
        }

        int perkAdded = 0;
        if (barrelId != null) {
            sb.append(barrelId);
            perkAdded++;
        }
        if (magazineId != null) {
            if (perkAdded > 0) {
                sb.append(",");
            }
            sb.append(magazineId);
            perkAdded++;
        }
        if (firstPerkId != null) {
            if (perkAdded > 0) {
                sb.append(",");
            }
            sb.append(firstPerkId);
            perkAdded++;
        }
        if (secondPerkId != null) {
            if (perkAdded > 0) {
                sb.append(",");
            }
            sb.append(secondPerkId);
            perkAdded++;
        }
        if (notes != null) {
            sb.append("#notes:");
            sb.append(notes);
        }
        return sb.toString();
    }
}
