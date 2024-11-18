package com.cash.user.controller;

import com.cash.user.model.User;
import com.cash.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok().body(service.getUsers());
    }
}
