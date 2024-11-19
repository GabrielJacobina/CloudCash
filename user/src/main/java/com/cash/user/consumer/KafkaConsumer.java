package com.cash.user.consumer;

import com.cash.user.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private final BalanceService balanceService;

    @KafkaListener(
            topics = "${topics.balance}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumeMessage(String message) throws IOException {
        logger.info("Solicitação recebida: " + message);
        balanceService.updateBalance(message);
    }
}
