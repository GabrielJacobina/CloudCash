package com.cash.transfer.service.impl;

import com.cash.transfer.dto.Transfer;
import com.cash.transfer.dto.TransferPayment;
import com.cash.transfer.dto.User;
import com.cash.transfer.producer.KafkaProducerService;
import com.cash.transfer.service.BalanceService;
import com.cash.transfer.service.TransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;

@RequiredArgsConstructor
@Service
public class TransferServiceImpl implements TransferService {

    private final BalanceService balanceService;
    private final KafkaProducerService kafkaProducerService;
    private final ObjectMapper objectMapper;

    @Override
    public String transfer(Transfer transfer) throws IOException {
        User userPayee = balanceService.getPayeeBalance(transfer.payee());
        User userPayer = balanceService.getPayerBalance(transfer.payer());
        sendPayment(new TransferPayment(transfer.value(), userPayer, userPayee));
        return "Pagamento enviado com sucesso";
    }

    private void sendPayment(TransferPayment transferPayment) throws IOException {
        StringWriter stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, transferPayment);
        kafkaProducerService.sendMessage(stringWriter.toString());
    }


}
