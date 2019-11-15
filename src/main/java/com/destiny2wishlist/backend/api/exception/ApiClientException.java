package com.destiny2wishlist.backend.api.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApiClientException extends Exception {
    private String ErrorStatus;
    private int ErrorCode;

    public ApiClientException(String message) {
        super(message);
    }

    public ApiClientException(String message, String errorStatus) {
        super(message);
        this.ErrorStatus = errorStatus;
    }

    public ApiClientException(String message, String errorStatus, int errorCode) {
        super(message);
        this.ErrorStatus = errorStatus;
        this.ErrorCode = errorCode;
    }

    public ApiClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiClientException(Throwable cause) {
        super(cause);
    }
}
