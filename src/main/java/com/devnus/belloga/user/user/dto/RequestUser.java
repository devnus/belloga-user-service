package com.devnus.belloga.user.user.dto;

import com.devnus.belloga.user.common.aop.annotation.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class RequestUser {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterOauthUser {
        @NotNull
        private String accountId;
        @NotNull
        private UserRole userRole;
        @NotNull
        private String email;
        @NotNull
        private String name;
        @NotNull
        private String phoneNumber;
        @NotNull
        private String birthYear;
    }
}
