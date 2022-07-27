package com.devnus.belloga.user.common.exception;

import com.devnus.belloga.user.common.dto.CommonResponse;
import com.devnus.belloga.user.common.dto.ErrorResponse;
import com.devnus.belloga.user.common.exception.error.EncryptException;
import com.devnus.belloga.user.common.exception.error.InvalidAccountIdException;
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

    /**
     * 사용자 정보를 요청하는 accountId가 유효하지 않은 accountId 일때
     */
    @ExceptionHandler(InvalidAccountIdException.class)
    protected ResponseEntity<CommonResponse> handleInvalidAccountIdException(InvalidAccountIdException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_ACCOUNT_ID_EXCEPTION;

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
