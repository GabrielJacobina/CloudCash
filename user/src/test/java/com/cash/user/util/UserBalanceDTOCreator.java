package com.cash.user.util;

import com.cash.user.dto.UserBalanceDTO;

public class UserBalanceDTOCreator {

    public static UserBalanceDTO createUserBalanceDTO() {
        return new UserBalanceDTO(1L, "Fulano", "123456789", 4525.02);
    }

    public static UserBalanceDTO createUserBalanceDTOToTransactionDTO() {
        return new UserBalanceDTO(3L, "teste", "123456789", 100.00);
    }
}
