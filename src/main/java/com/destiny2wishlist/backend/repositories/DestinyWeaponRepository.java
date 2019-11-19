package com.destiny2wishlist.backend.repositories;

import com.destiny2wishlist.backend.entities.DestinyWeapon;
import org.springframework.data.repository.CrudRepository;

public interface DestinyWeaponRepository extends CrudRepository<DestinyWeapon, Long> {
}
