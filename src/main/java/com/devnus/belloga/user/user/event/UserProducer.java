package com.devnus.belloga.user.user.event;

import com.devnus.belloga.user.user.dto.EventAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value(value = "${app.topic.account.register-enterprise-saga}")
    private String REGISTER_ENTERPRISE_SAGA_TOPIC;
    @Value(value = "${app.topic.account.register-labeler-saga}")
    private String REGISTER_LABELER_SAGA_TOPIC;

    public void sendRegisterEnterpriseSagaResult(String accountId, boolean status) {
        kafkaTemplate.send(REGISTER_ENTERPRISE_SAGA_TOPIC, EventAccount.RegisterEnterpriseSaga.builder()
                        .accountId(accountId)
                        .isSuccess(status)
                .build());
    }
    public void sendRegisterLabelerSagaResult(String accountId, boolean status) {
        kafkaTemplate.send(REGISTER_LABELER_SAGA_TOPIC, EventAccount.RegisterLabelerSaga.builder()
                .accountId(accountId)
                .isSuccess(status)
                .build());
    }

}