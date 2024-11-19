package com.cash.user.service;

import com.cash.user.dto.UserBalanceDTO;
import com.cash.user.dto.UserDTO;
import com.cash.user.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    void createUser(UserDTO user);

    UserBalanceDTO getUserById(Long id);
}
