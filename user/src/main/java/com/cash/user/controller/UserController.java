package com.cash.user.controller;

import com.cash.user.dto.LoginDTO;
import com.cash.user.dto.UserBalanceDTO;
import com.cash.user.dto.UserDTO;
import com.cash.user.model.User;
import com.cash.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService service;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(service.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBalanceDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getUserById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<User> addUser(@RequestBody UserDTO user) {
        service.createUser(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/login/{username}")
    public ResponseEntity<LoginDTO> getUserByEmail(@PathVariable String username) {
        return ResponseEntity.ok().body(service.getUserByEmail(username));
    }
}
