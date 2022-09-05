package com.devnus.belloga.user.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EventAccount {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterEnterprise {
        private String accountId;
        private String email;
        private String name;
        private String phoneNumber;
        private String organization;;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterLabeler {
        private String accountId;
        private String email;
        private String name;
        private String phoneNumber;
        private String birthYear;
    }
}
