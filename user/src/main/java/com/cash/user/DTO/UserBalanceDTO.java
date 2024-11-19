package com.cash.user.DTO;

public record UserBalanceDTO(Long userId, String name, String cpfCnpj, double balance) {
}
