package com.cash.transfer.dto;

public record Transfer(double value, Long payer, Long payee) {
}
