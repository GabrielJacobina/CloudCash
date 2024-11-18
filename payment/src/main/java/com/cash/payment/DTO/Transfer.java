package com.cash.payment.DTO;

public record Transfer(double value, Integer payer, Integer payee) {
}
