package com.cash.payment.consumer;

import com.cash.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private final PaymentService paymentService;

    @KafkaListener(
            topics = "${topics.payment}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumeMessage(String message) throws IOException {
        System.out.println("Solicitação recebida: " + message);
        paymentService.makePayment(message);
    }
}
