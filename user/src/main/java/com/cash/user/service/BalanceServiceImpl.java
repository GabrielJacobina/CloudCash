package com.cash.user.service;

import com.cash.user.dto.TransactionDTO;
import com.cash.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BalanceServiceImpl implements BalanceService {

    private static final Logger logger = LoggerFactory.getLogger(BalanceServiceImpl.class);

    private final ObjectMapper mapper = new ObjectMapper();
    private final UserRepository userRepository;

    @Override
    public void updateBalance(String message) throws JsonProcessingException {
        TransactionDTO transaction = converMessageInObject(message);
        transaction.getUsers().forEach(user -> userRepository.updateBalance(user.userId(), user.balance()));
        logger.info("Updated user balance: " + mapper.writeValueAsString(transaction));
    }

    TransactionDTO converMessageInObject(String message) throws JsonProcessingException {
        return mapper.readValue(message, TransactionDTO.class);
    }
}
