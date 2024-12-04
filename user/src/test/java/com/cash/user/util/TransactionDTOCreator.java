package com.cash.user.util;

import com.cash.user.dto.TransactionDTO;

import java.util.List;

public class TransactionDTOCreator {

    public static TransactionDTO createTransactionDTO() {
        return new TransactionDTO(List.of(UserBalanceDTOCreator.createUserBalanceDTOToTransactionDTO()));
    }
}
