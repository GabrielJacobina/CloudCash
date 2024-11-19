package com.cash.transfer.service.impl;

import com.cash.transfer.DTO.Transfer;
import com.cash.transfer.DTO.TransferPayment;
import com.cash.transfer.DTO.User;
import com.cash.transfer.producer.KafkaProducerService;
import com.cash.transfer.service.BalanceService;
import com.cash.transfer.service.TransferService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransferServiceImpl implements TransferService {

    private final BalanceService balanceService;
    private final KafkaProducerService kafkaProducerService;
    private final ObjectMapper objectMapper;

    @Override
    public String transfer(Transfer transfer) throws JsonProcessingException {
        User userPayee = balanceService.getPayeeBalance(transfer.payee());
        User userPayer = balanceService.getPayerBalance(transfer.payer());
        sendPayment(new TransferPayment(transfer.value(), userPayer, userPayee));
        return "Pagamento enviado com sucesso";
    }

    private void sendPayment(TransferPayment transferPayment) throws JsonProcessingException {
        String message = objectMapper.writerWithView(TransferPayment.class).writeValueAsString(transferPayment);
        kafkaProducerService.sendMessage(message);
    }


}
