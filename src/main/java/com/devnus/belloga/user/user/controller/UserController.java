package com.devnus.belloga.user.user.controller;

import com.devnus.belloga.user.common.aop.annotation.GetAccountIdentification;
import com.devnus.belloga.user.common.aop.annotation.UserRole;
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

    /**
     * 헤더의 accountId를 통해 현재 기업 사용자 정보 조회
     */
    @GetMapping("/v1/enterprise")
    public ResponseEntity<CommonResponse> getEnterpriseUserInfo(@GetAccountIdentification(role = UserRole.ENTERPRISE) String enterpriseId) {

        CommonResponse response = CommonResponse.builder()
                .success(true)
                .response(userService.getEnterpriseInfo(enterpriseId))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 헤더의 accountId를 통해 현재 라벨러 사용자 정보 조회
     */
    @GetMapping("/v1/labeler")
    public ResponseEntity<CommonResponse> getLabelerUserInfo(@GetAccountIdentification(role = UserRole.LABELER) String labelerId) {

        CommonResponse response = CommonResponse.builder()
                .success(true)
                .response(userService.getLabelerInfo(labelerId))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
