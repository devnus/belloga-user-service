package com.devnus.belloga.user.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    ENCRYPT_FAILED_EXCEPTION(HttpStatus.NOT_ACCEPTABLE, "USER_001", "ENCRYPT_FAILED");
    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(final HttpStatus status, final String code, final String message){
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
