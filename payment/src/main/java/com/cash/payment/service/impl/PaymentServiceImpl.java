package com.cash.payment.service.impl;

import com.cash.payment.dto.TransferPaymentDTO;
import com.cash.payment.dto.UserDTO;
import com.cash.payment.model.Transaction;
import com.cash.payment.repository.TransactionRepository;
import com.cash.payment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final ObjectMapper mapper = new ObjectMapper();
    private final TransactionRepository repository;

    @Override
    public void makePayment(String message) throws IOException {
        StringWriter stringWriter = new StringWriter();
        TransferPaymentDTO transferPaymentDTO = converMessageInObject(message);
        if (transferPaymentDTO.getPayer() != null) {
            debitValue(transferPaymentDTO.getValue(), transferPaymentDTO.getPayer());
            creditValue(transferPaymentDTO.getValue(), transferPaymentDTO.getPayee());
            mapper.writeValue(stringWriter, transferPaymentDTO);
            logger.info("Payment made: " + stringWriter);
            saveTransaction(transferPaymentDTO, true);
        } else {
            mapper.writeValue(stringWriter, transferPaymentDTO);
            logger.error("Payment failed: " + stringWriter);
            saveTransaction(transferPaymentDTO, false);
        }
    }

    TransferPaymentDTO converMessageInObject(String message) throws JsonProcessingException {
        return mapper.readValue(message, TransferPaymentDTO.class);
    }

    void debitValue(double value, UserDTO userDTO) {
        userDTO.setBalance(userDTO.getBalance() - value);
    }

    void creditValue(double value, UserDTO userDTO) {
        userDTO.setBalance(userDTO.getBalance() + value);
    }

    Transaction saveTransaction(TransferPaymentDTO transferPaymentDTO, boolean completed) {
        Transaction transaction = Transaction.builder()
                .payer(transferPaymentDTO.getPayer())
                .payee(transferPaymentDTO.getPayee())
                .amount(transferPaymentDTO.getValue())
                .completed(completed)
                .date(LocalDateTime.now())
                .build();
        return repository.save(transaction);
    }
}
