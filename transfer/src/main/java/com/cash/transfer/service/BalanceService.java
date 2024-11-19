package com.cash.transfer.service;

import com.cash.transfer.DTO.User;

public interface BalanceService {

    User getPayerBalance(Long idUser);

    User getPayeeBalance(Long idPayee);

}
