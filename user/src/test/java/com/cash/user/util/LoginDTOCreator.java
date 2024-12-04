package com.cash.user.util;

import com.cash.user.dto.LoginDTO;

public class LoginDTOCreator {

    public static LoginDTO createLoginDTO() {
        return new LoginDTO("teste@teste.com", "Teste@123");
    }
}
