package com.cash.transfer.service;

import com.cash.transfer.dto.User;

public interface BalanceService {

    User getPayerBalance(Long idUser);

    User getPayeeBalance(Long idPayee);

}
