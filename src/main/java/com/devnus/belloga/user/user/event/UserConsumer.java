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
    private final UserProducer userProducer;
    private final UserService userService;
    /**
     * 기업 사용자 custom 회원가입시 consume
     * 컨슘 결과를 사가 패턴으로 보낸다.
     */
    @KafkaListener(topics = "register-enterprise", groupId = "register-enterprise-1", containerFactory = "eventRegisterEnterpriseListener")
    protected void registerEnterprise(EventAccount.RegisterEnterprise event) throws IOException {
        try {
            if(userService.saveEnterprise(event)) {
                userProducer.sendRegisterEnterpriseSagaResult(event.getAccountId(), true);
            }
        } catch (Exception e) {
            userProducer.sendRegisterEnterpriseSagaResult(event.getAccountId(), false);
        }
    }

    /**
     * 관리자 custom 회원가입시 consume
     * 컨슘 결과를 사가 패턴으로 보낸다.
     */
    @KafkaListener(topics = "register-admin", groupId = "register-admin-1", containerFactory = "eventRegisterAdminListener")
    protected void registerAdmin(EventAccount.RegisterAdmin event) throws IOException {
        try {
            if(userService.saveAdmin(event)) {
                userProducer.sendRegisterAdminSagaResult(event.getAccountId(), true);
            }
        } catch (Exception e) {
            userProducer.sendRegisterAdminSagaResult(event.getAccountId(), false);
        }
    }

    /**
     * 일반 사용자 회원가입시 consume
     * 컨슘 결과를 사가 패턴으로 보낸다.
     */
    @KafkaListener(topics = "register-labeler", groupId = "register-labeler-1", containerFactory = "eventRegisterLabelerListener")
    protected void registerLabeler(EventAccount.RegisterLabeler event) throws IOException {
        try {
            if(userService.saveLabeler(event)) {
                userProducer.sendRegisterLabelerSagaResult(event.getAccountId(), true);
            }
        } catch (Exception e) {
            userProducer.sendRegisterLabelerSagaResult(event.getAccountId(), false);
        }
    }
}
