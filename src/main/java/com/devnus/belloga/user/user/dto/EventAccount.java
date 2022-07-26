package com.devnus.belloga.user.user.dto;

import com.devnus.belloga.user.common.aop.annotation.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EventAccount {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterAccountEnterprise {
        private String accountId;
        private String email;
        private String name;
        private String phoneNumber;
        private String organization;;
        private UserRole userRole;
    }
}
