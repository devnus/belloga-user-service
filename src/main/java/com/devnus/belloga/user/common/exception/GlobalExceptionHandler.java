package com.devnus.belloga.user.common.exception;

import com.devnus.belloga.user.common.dto.CommonResponse;
import com.devnus.belloga.user.common.dto.ErrorResponse;
import com.devnus.belloga.user.common.exception.error.EncryptException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 사용자 주요 정보의 암호화 과정이 실패 했을때
     */
    @ExceptionHandler(EncryptException.class)
    protected ResponseEntity<CommonResponse> handleEncryptException(EncryptException ex) {
        ErrorCode errorCode = ErrorCode.ENCRYPT_FAILED_EXCEPTION;

        ErrorResponse error = ErrorResponse.builder()
                .status(errorCode.getStatus().value())
                .message(errorCode.getMessage())
                .code(errorCode.getCode())
                .build();

        CommonResponse response = CommonResponse.builder()
                .success(false)
                .error(error)
                .build();

        return new ResponseEntity<>(response, errorCode.getStatus());
    }

}
