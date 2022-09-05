package com.devnus.belloga.user.user.service;

import com.devnus.belloga.user.user.dto.EventAccount;
import com.devnus.belloga.user.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
class UserServiceImplTest {

    UserServiceImpl userService;

    @Autowired
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
    void saveEnterpriseTest() {
        //given
        EventAccount.RegisterEnterprise event = EventAccount.RegisterEnterprise.builder()
                .phoneNumber("01000000000")
                .organization("sample-organization")
                .name("sample-name")
                .email("sample@sample.sample")
                .accountId("sample-accountId")
                .build();

        //when
        boolean result = userService.saveEnterprise(event);

        //then
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    void saveLabelerTest() {
        //given
        EventAccount.RegisterLabeler event = EventAccount.RegisterLabeler.builder()
                .phoneNumber("01000000000")
                .name("sample-name")
                .email("sample@sample.sample")
                .accountId("sample-accountId")
                .birthYear("2000")
                .build();

        //when
        boolean result = userService.saveLabeler(event);

        //then
        Assertions.assertThat(result).isEqualTo(true);
    }

}