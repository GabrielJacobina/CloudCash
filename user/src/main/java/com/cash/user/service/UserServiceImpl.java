package com.cash.user.service;

import com.cash.user.DTO.UserBalanceDTO;
import com.cash.user.DTO.UserDTO;
import com.cash.user.model.User;
import com.cash.user.repository.UserRepository;
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
        return userDTOById.isPresent() ? userDTOById.get() : null;

    }
}
