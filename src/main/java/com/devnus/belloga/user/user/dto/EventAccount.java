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
    public static class RegisterAdmin {
        private String accountId;
        private String email;
        private String name;
        private String phoneNumber;
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

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterEnterpriseSaga {
        private String accountId;
        private boolean isSuccess;
    }
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterLabelerSaga {
        private String accountId;
        private boolean isSuccess;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterAdminSaga {
        private String accountId;
        private boolean isSuccess;
    }
}
