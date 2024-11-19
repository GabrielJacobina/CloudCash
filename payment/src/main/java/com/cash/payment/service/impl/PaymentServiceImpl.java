package com.cash.payment.service.impl;

import com.cash.payment.dto.TransactionDTO;
import com.cash.payment.dto.TransferPaymentDTO;
import com.cash.payment.dto.UserDTO;
import com.cash.payment.model.Transaction;
import com.cash.payment.producer.KafkaProducer;
import com.cash.payment.repository.TransactionRepository;
import com.cash.payment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final ObjectMapper mapper = new ObjectMapper();
    private final TransactionRepository repository;
    private final KafkaProducer kafkaProducer;

    @Override
    public void makePayment(String message) throws IOException {
        TransferPaymentDTO transferPaymentDTO = converMessageInObject(message);

        if (transferPaymentDTO.getPayer() != null) {
            debitValue(transferPaymentDTO.getValue(), transferPaymentDTO.getPayer());
            creditValue(transferPaymentDTO.getValue(), transferPaymentDTO.getPayee());

            logger.info("Payment made: " + mapper.writeValueAsString(transferPaymentDTO));

            Transaction transaction = saveTransaction(transferPaymentDTO, true);
            sendTransaction(transaction);
        } else {
            logger.error("Payment failed: " + mapper.writeValueAsString(transferPaymentDTO));
            saveTransaction(transferPaymentDTO, false);
        }
    }

    private TransferPaymentDTO converMessageInObject(String message) throws JsonProcessingException {
        return mapper.readValue(message, TransferPaymentDTO.class);
    }

    private void debitValue(double value, UserDTO userDTO) {
        userDTO.setBalance(userDTO.getBalance() - value);
    }

    private void creditValue(double value, UserDTO userDTO) {
        userDTO.setBalance(userDTO.getBalance() + value);
    }

    private Transaction saveTransaction(TransferPaymentDTO transferPaymentDTO, boolean completed) {
        Transaction transaction = Transaction.builder()
                .payer(transferPaymentDTO.getPayer())
                .payee(transferPaymentDTO.getPayee())
                .amount(transferPaymentDTO.getValue())
                .completed(completed)
                .date(LocalDateTime.now())
                .build();
        return repository.save(transaction);
    }

    private void sendTransaction(Transaction transaction) throws IOException {
        List<UserDTO> users = List.of(transaction.getPayer(), transaction.getPayee());
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .users(users)
                .build();

        kafkaProducer.sendMessage(mapper.writeValueAsString(transactionDTO));
    }
}
