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
     */
    @KafkaListener(topics = "register-enterprise", groupId = "register-enterprise-1", containerFactory = "eventRegisterEnterpriseListener")
    protected boolean registerEnterprise(EventAccount.RegisterEnterprise event) throws IOException {
        return userService.saveEnterprise(event);
    }

    /**
     * 일반 사용자 custom 회원가입시 consume
     */
    @KafkaListener(topics = "register-labeler", groupId = "register-labeler-1", containerFactory = "eventRegisterLabelerListener")
    protected boolean registerLabeler(EventAccount.RegisterLabeler event) throws IOException {
        return userService.saveLabeler(event);
    }
}
