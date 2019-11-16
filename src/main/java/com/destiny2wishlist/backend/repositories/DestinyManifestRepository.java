package com.destiny2wishlist.backend.repositories;

import com.destiny2wishlist.backend.entities.DestinyManifest;
import org.springframework.data.repository.CrudRepository;

public interface DestinyManifestRepository extends CrudRepository<DestinyManifest, Long> {
}
