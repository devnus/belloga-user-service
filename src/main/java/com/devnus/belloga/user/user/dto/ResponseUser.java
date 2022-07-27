package com.devnus.belloga.user.user.dto;

import com.devnus.belloga.user.common.aop.annotation.UserRole;
import com.devnus.belloga.user.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ResponseUser {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo {
        private String userId;
        private UserRole userRole;
    }
}
