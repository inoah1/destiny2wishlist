package com.destiny2wishlist.backend.repositories;

import com.destiny2wishlist.backend.entities.DestinyWeapon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DestinyWeaponRepository extends CrudRepository<DestinyWeapon, Long> {

    List<DestinyWeapon> findByNameContainingIgnoreCase(String name);

}
