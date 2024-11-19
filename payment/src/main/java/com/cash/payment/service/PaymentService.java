package com.cash.payment.service;

import java.io.IOException;

public interface PaymentService {

    void makePayment(String message) throws IOException;
}
