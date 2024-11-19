package com.cash.user.DTO;

public record UserBalanceDTO(Long userId, String name, String CPFCNPJ, double balance) {
}
