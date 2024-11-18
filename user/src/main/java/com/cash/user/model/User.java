package com.cash.user.model;

import com.cash.user.enums.UserTypeEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@ToString
@AllArgsConstructor
@Getter
@Setter
@Document
public class User {

    @Id
    private String id;
    @Field("CPF/CNPJ")
    @Indexed(unique = true)
    private String CPFCNPJ;
    private String name;
    private Contact contact;
    private UserTypeEnum userTypeEnum;
    private double balance;

}
