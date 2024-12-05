package com.cash.user.util;

import com.cash.user.enums.UserTypeEnum;
import com.cash.user.model.User;

public class UserCreator {

    public static User createUser() {
        return User.builder()
                .id(1L)
                .name("Fulano")
                .cpfCnpj("213456789")
                .contact(ContactCreator.createContact())
                .userTypeEnum(UserTypeEnum.INDIVIDUAL)
                .balance(4525.02)
                .build();
    }

    public static User createUserSave() {
        return User.builder()
                .name("Fulano")
                .cpfCnpj("123446789")
                .contact(ContactCreator.createContact())
                .userTypeEnum(UserTypeEnum.INDIVIDUAL)
                .balance(4525.02)
                .build();
    }
}
