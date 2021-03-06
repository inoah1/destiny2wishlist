package com.destiny2wishlist.backend.services;


import com.destiny2wishlist.backend.api.dto.DestinyManifest;
import com.destiny2wishlist.backend.api.dto.DestinyManifestResponse;
import com.destiny2wishlist.backend.api.exception.ApiClientException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class BungieApiClient {

    private final IRequest request;
    @Value("${bungie.host}")
    private String apiRoot;
    private ObjectMapper mapper = null;

    public BungieApiClient(IRequest request) {
        this.request = request;
        mapper = new ObjectMapper();
    }

    private String formUrl(String pattern, Object... arr) {
        return this.apiRoot + MessageFormat.format(pattern, arr);
    }

    public DestinyManifestResponse getManifestInfo() throws ApiClientException {
        try {
            String url = formUrl("/Platform/Destiny2/Manifest/");
            String data = request.getUrl(url);

            DestinyManifestResponse response = mapper.readValue(data, DestinyManifestResponse.class);
            if (response.getErrorCode() != 1) {
                throw new ApiClientException(response.getMessage(), response.getErrorStatus(), response.getErrorCode());
            }
            return response;
        } catch (Exception e) {
            throw new ApiClientException(e);
        }
    }

    public DestinyManifest getManifestJson(String jsonPath) throws ApiClientException {
        try {
            String url = formUrl(jsonPath);
            String data = request.getUrl(url);

            return mapper.readValue(data, DestinyManifest.class);
        } catch (Exception e) {
            throw new ApiClientException(e);
        }
    }
}
