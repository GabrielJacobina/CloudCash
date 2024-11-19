package com.cash.transfer.service.impl;

import com.cash.transfer.DTO.Transfer;
import com.cash.transfer.DTO.User;
import com.cash.transfer.service.BalanceService;
import com.cash.transfer.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransferServiceImpl implements TransferService {

    private final BalanceService balanceService;

    @Override
    public Transfer transfer(Transfer transfer) {
        User userPayee = balanceService.getPayeeBalance(transfer.payee());
        User userPayer = balanceService.getPayerBalance(transfer.payer());
        return null;
    }

    private void makePayment(User userPayee, User userPayer, double amount) {

    }


}
