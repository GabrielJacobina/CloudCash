package com.cash.user.dto;

public record UserBalanceDTO(Long userId, String name, String cpfCnpj, double balance) {
}
