package com.devnus.belloga.user.user.controller;

import com.devnus.belloga.user.common.dto.CommonResponse;
import com.devnus.belloga.user.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 유저 정보 관련 컨트롤러
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * accountId를 이용해 기업 사용자 정보 요청
     */
    @GetMapping("/v1/enterprise/{accountId}")
    public ResponseEntity<CommonResponse> requestEnterpriseUserInfo(@PathVariable String accountId) {

        CommonResponse response = CommonResponse.builder()
                .success(true)
                .response(userService.getEnterpriseInfo(accountId))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * accountId를 이용해 일반 사용자 정보 요청
     */
    @GetMapping("/v1/labelers/{accountId}")
    public ResponseEntity<CommonResponse> requestLabelerUserInfo(@PathVariable String accountId) {

        CommonResponse response = CommonResponse.builder()
                .success(true)
                .response(userService.getLabelerInfo(accountId))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
