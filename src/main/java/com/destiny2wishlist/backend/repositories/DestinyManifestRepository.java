package com.destiny2wishlist.backend.repositories;

import com.destiny2wishlist.backend.entities.DestinyManifestEntity;
import org.springframework.data.repository.CrudRepository;

public interface DestinyManifestRepository extends CrudRepository<DestinyManifestEntity, String> {

    DestinyManifestEntity findTopByOrderByUpdatedAtDesc();

}
