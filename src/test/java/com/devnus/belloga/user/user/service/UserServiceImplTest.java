package com.devnus.belloga.user.user.service;

import com.devnus.belloga.user.common.aop.annotation.UserRole;
import com.devnus.belloga.user.user.dto.EventAccount;
import com.devnus.belloga.user.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class UserServiceImplTest {

    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this); //Mock어노테이션 필드 초기화
        userService = new UserServiceImpl(userRepository);
    }

    @AfterEach
    void closeMock() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

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