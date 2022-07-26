package com.devnus.belloga.user.user.event;

import com.devnus.belloga.user.user.dto.EventAccount;
import com.devnus.belloga.user.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UserConsumer {

    private final UserService userService;
    /**
     * 기업 사용자 custom 회원가입시 consume
     * group은 협의 후 결정
     */
    @KafkaListener(topics = "register-account-enterprise", groupId = "register-account-enterprise-1")
    protected boolean registerCustomAccountEnterprise(Object event) throws IOException {
        return userService.saveUserEnterprise((EventAccount.RegisterAccountEnterprise) event);
    }
}
