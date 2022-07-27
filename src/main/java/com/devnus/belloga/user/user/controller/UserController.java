package com.devnus.belloga.user.user.controller;

import com.devnus.belloga.user.common.dto.CommonResponse;
import com.devnus.belloga.user.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 유저 정보 관련 컨트롤러
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Auth(인증) 마이크로서비스에서 토큰을 만들기 위해 accountId를 이용해 유저 정보를 요청
     */
    @GetMapping("/v1/users/accounts/{accountId}")
    public ResponseEntity<CommonResponse> requestUserInfo(@PathVariable String accountId) {

        CommonResponse response = CommonResponse.builder()
                .success(true)
                .response(userService.getUserInfoByAccountId(accountId))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
