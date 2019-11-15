package com.destiny2wishlist.backend.services;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@NoArgsConstructor
@Slf4j
public class Request implements IRequest {

    private RestTemplate restTemplate;

    @Value("${bungie.apiKey}")
    private String apiKey;

    @Autowired
    public Request(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getUrl(String url) {
        log.info("Making request to " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKey);
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if (response != null) {
            log.info("http-response: " + response.getBody());

            return response.getBody();
        }
        return null;
    }
}
