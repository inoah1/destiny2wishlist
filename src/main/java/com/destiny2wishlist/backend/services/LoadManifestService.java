package com.destiny2wishlist.backend.services;

import com.destiny2wishlist.backend.api.dto.DestinyManifestInfo;
import com.destiny2wishlist.backend.api.dto.DestinyManifestResponse;
import com.destiny2wishlist.backend.api.exception.ApiClientException;
import com.destiny2wishlist.backend.entities.DestinyManifest;
import com.destiny2wishlist.backend.repositories.DestinyManifestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadManifestService {

    @Autowired
    DestinyApiClient destinyApiClient;

    @Autowired
    private DestinyManifestRepository manifestRepository;

    public void loadDestinyManifest() throws ApiClientException {

        DestinyManifestResponse manifestResponse = destinyApiClient.getManifestInfo();

        if (manifestResponse != null) {
            DestinyManifestInfo manifestInfo = manifestResponse.getResponse();

            if (manifestInfo != null) {
                DestinyManifest destinyManifest = new DestinyManifest(manifestInfo.getVersion(), manifestInfo.getJsonWorldContentPaths().getEnJsonPath());
                manifestRepository.save(destinyManifest);
            }
        }
    }
}
