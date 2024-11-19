package com.cash.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface BalanceService {

    void updateBalance(String message) throws JsonProcessingException;
}
