package com.cash.transfer.DTO;

public record Transfer(double value, Long payer, Long payee) {
}
