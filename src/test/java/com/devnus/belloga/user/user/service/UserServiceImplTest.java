package com.devnus.belloga.user.user.service;

import com.devnus.belloga.user.common.aop.annotation.UserRole;
import com.devnus.belloga.user.user.dto.EventAccount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    void saveUserEnterpriseTest() {
        //given
        EventAccount.RegisterAccountEnterprise event = EventAccount.RegisterAccountEnterprise.builder()
                .phoneNumber("01000000000")
                .userRole(UserRole.ENTERPRISE)
                .organization("sample-organization")
                .name("sample-name")
                .email("sample@sample.sample")
                .accountId("sample-accountId")
                .build();

        //when
        boolean result = userService.saveUserEnterprise(event);

        //then
        Assertions.assertThat(result).isEqualTo(true);
    }

}