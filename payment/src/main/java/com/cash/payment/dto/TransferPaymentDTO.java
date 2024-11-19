package com.cash.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransferPaymentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private double value;
    private UserDTO payer;
    private UserDTO payee;


}
