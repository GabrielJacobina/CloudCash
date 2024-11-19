package com.cash.transfer.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransferPayment implements Serializable {
    private static final long serialVersionUID = 1L;

    private double value;
    private User payer;
    private User payee;


}
