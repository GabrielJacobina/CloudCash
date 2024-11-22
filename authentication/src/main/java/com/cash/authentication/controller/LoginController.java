package com.cash.authentication.controller;

import com.cash.authentication.dto.LoginDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

//    @PostMapping("/login")
//    public String login(@RequestBody LoginDTO login) {
//        return "Chegou a request com so dados: " + login.username() + " " + login.password();
//    }

    @GetMapping("/teste")
    public String requestTeste() {
        return "Requisição fieta com sucesso";
    }

}
