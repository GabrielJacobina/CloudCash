package com.cash.payment.model;

import com.cash.payment.dto.User;
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
    private User payer;
    private User payee;
    private double amount;
    private boolean completed;
    private LocalDateTime date;
}
