package com.cash.user.service.impl;

import com.cash.user.dto.LoginDTO;
import com.cash.user.dto.UserBalanceDTO;
import com.cash.user.dto.UserDTO;
import com.cash.user.model.User;
import com.cash.user.repository.UserRepository;
import com.cash.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.name())
                .cpfCnpj(userDTO.cpfCnpj())
                .contact(userDTO.contact())
                .userTypeEnum(userDTO.userType())
                .balance(userDTO.balance())
                .build();

        userRepository.save(user);
    }

    @Override
    public UserBalanceDTO getUserById(Long id) {
        Optional<UserBalanceDTO> userDTOById = userRepository.findUserDTOById(id);
        return userDTOById.orElse(null);

    }

    @Override
    public LoginDTO getUserByEmail(String email) {
        return userRepository.getUserByEmail(email).orElse(null);
    }
}
