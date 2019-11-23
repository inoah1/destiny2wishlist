package com.destiny2wishlist.backend.repositories;

import com.destiny2wishlist.backend.entities.DestinyManifest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DestinyManifestRepository extends CrudRepository<DestinyManifest, String> {

    @Query(value = "select * from destiny_manifest dm where dm.is_latest = true order by updated_at desc", nativeQuery = true)
    List<DestinyManifest> findLatestManifest();

    @Query("update destiny_manifest dm set dm.is_latest = false")
    int archiveOldManifest();
}
