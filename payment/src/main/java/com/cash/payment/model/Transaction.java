package com.cash.payment.model;

import com.cash.payment.dto.UserDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Transaction {

    @Id
    private String id;
    private UserDTO payer;
    private UserDTO payee;
    private double amount;
    private boolean completed;
    private LocalDateTime date;
}
