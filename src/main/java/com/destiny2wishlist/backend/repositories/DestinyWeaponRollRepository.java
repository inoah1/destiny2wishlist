package com.destiny2wishlist.backend.repositories;

import com.destiny2wishlist.backend.entities.DestinyWeaponRoll;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DestinyWeaponRollRepository extends CrudRepository<DestinyWeaponRoll, Long> {

    List<DestinyWeaponRoll> findByWeaponNameContainingIgnoreCase(String weaponName);
}
