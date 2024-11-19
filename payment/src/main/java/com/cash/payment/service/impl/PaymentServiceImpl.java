package com.cash.payment.service.impl;

import com.cash.payment.dto.TransferPayment;
import com.cash.payment.dto.User;
import com.cash.payment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void makePayment(String message) throws IOException {
        StringWriter stringWriter = new StringWriter();
        TransferPayment transferPayment = converMessageInObject(message);
        if (transferPayment.getPayer() != null) {
            debitValue(transferPayment.getValue(), transferPayment.getPayer());
            creditValue(transferPayment.getValue(), transferPayment.getPayee());
            mapper.writeValue(stringWriter, transferPayment);
            System.out.println("Payment made: " + stringWriter);
        } else {
            mapper.writeValue(stringWriter, transferPayment);
            System.out.println("Payment failed: " + stringWriter);
        }
    }

    TransferPayment converMessageInObject(String message) throws JsonProcessingException {
        return mapper.readValue(message, TransferPayment.class);
    }

    User debitValue(double value, User user) {
        user.setBalance(user.getBalance() - value);
        return user;
    }

    User creditValue(double value, User user) {
        user.setBalance(user.getBalance() + value);
        return user;
    }
}
