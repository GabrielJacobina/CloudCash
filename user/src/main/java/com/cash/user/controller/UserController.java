package com.cash.user.controller;

import com.cash.user.DTO.UserDTO;
import com.cash.user.model.User;
import com.cash.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(service.getUsers());
    }

    @PostMapping("/create")
    public ResponseEntity<User> addUser(@RequestBody UserDTO user) {
        service.createUser(user);
        return ResponseEntity.noContent().build();
    }
}
