package com.cash.user.model;

import com.cash.user.enums.UserTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "TAB_USER")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false, name = "CPF_CNPJ")
    private String cpfCnpj;
    private String name;
    @Embedded
    private Contact contact;
    private UserTypeEnum userTypeEnum;
    private double balance;

}
