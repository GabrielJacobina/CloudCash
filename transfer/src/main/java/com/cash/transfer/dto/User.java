package com.cash.transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String name;
    private String cpfCnpj;
    private double balance;
}
