package com.cash.user.service.impl;

import com.cash.user.dto.LoginDTO;
import com.cash.user.dto.UserBalanceDTO;
import com.cash.user.dto.UserDTO;
import com.cash.user.model.User;
import com.cash.user.repository.UserRepository;
import com.cash.user.util.LoginDTOCreator;
import com.cash.user.util.UserBalanceDTOCreator;
import com.cash.user.util.UserCreator;
import com.cash.user.util.UserDTOCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    @Test
    void getUsers_WhenCalled_ShouldReturnUsers() {
        Long idUser = UserCreator.createUser().getId();
        BDDMockito.given(userRepository.findAll()).willReturn(List.of(UserCreator.createUser()));

        List<User> users = userService.getUsers();

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(idUser, users.get(0).getId());
    }

    @Test
    void createUser_WithUserDTO_ShouldCreateUser() {
        UserDTO userDTO = UserDTOCreator.createUserDTO();
        userService.createUser(userDTO);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userArgumentCaptor.capture());

        User user = userArgumentCaptor.getValue();
        assertNotNull(user);
        assertEquals(userDTO.name(), user.getName());
        assertEquals(userDTO.cpfCnpj(), user.getCpfCnpj());
    }

    @Test
    void getUserById_WhenIdExisting_ShouldReturnUser() {
        UserBalanceDTO userMock = UserBalanceDTOCreator.createUserBalanceDTO();
        BDDMockito.given(userRepository.findUserDTOById(userMock.userId())).willReturn(Optional.of(userMock));

        UserBalanceDTO user = userService.getUserById(userMock.userId());

        assertNotNull(user);
        assertEquals(userMock.userId(), user.userId());
        assertEquals(userMock.name(), user.name());
        assertEquals(userMock.balance(), user.balance());
    }

    @Test
    void getUserById_WhenIdDoesNotExist_ShouldReturnNull() {
        UserBalanceDTO userMock = UserBalanceDTOCreator.createUserBalanceDTO();
        BDDMockito.given(userRepository.findUserDTOById(userMock.userId())).willReturn(Optional.empty());

        UserBalanceDTO user = userService.getUserById(userMock.userId());

        assertNull(user);
    }

    @Test
    void getUserByEmail_WhenEmailExists_ShouldReturnUser() {
        LoginDTO loginMock = LoginDTOCreator.createLoginDTO();
        BDDMockito.given(userRepository.getUserByEmail(loginMock.username())).willReturn(Optional.of(loginMock));

        LoginDTO loginDTO = userService.getUserByEmail(loginMock.username());

        assertNotNull(loginDTO);
        assertEquals(loginMock.username(), loginDTO.username());
    }

    @Test
    void getUserByEmail_WhenEmaillDoesNotExist_ShouldReturnNull() {
        LoginDTO loginMock = LoginDTOCreator.createLoginDTO();
        BDDMockito.given(userRepository.getUserByEmail(loginMock.username())).willReturn(Optional.empty());

        LoginDTO loginDTO = userService.getUserByEmail(loginMock.username());

        assertNull(loginDTO);
    }
}
