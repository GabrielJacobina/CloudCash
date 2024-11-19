package com.cash.transfer.service.impl;

import com.cash.transfer.dto.User;
import com.cash.transfer.client.UserClient;
import com.cash.transfer.config.CustomException;
import com.cash.transfer.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final UserClient userClient;

    @Override
    public User getPayerBalance(Long idUser) {
        User user = userClient.getUser(idUser);
        if (user.getBalance() <= 0) {
            throw new CustomException("Saldo do pagador é insuficiente", HttpStatus.BAD_REQUEST);
        }
        return user;
    }


    @Override
    public User getPayeeBalance(Long idPayee) {
        return userClient.getUser(idPayee);
    }
}
