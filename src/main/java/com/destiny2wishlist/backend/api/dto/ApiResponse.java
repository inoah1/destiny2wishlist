package com.destiny2wishlist.backend.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {

    @JsonProperty("ErrorCode")
    private int errorCode;

    @JsonProperty("ThrottleSeconds")
    private int throttleSeconds;

    @JsonProperty("ErrorStatus")
    private String errorStatus;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("MessageData")
    private Object messageData;
}
