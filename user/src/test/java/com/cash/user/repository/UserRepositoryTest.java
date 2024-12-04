package com.cash.user.repository;

import com.cash.user.dto.LoginDTO;
import com.cash.user.dto.UserBalanceDTO;
import com.cash.user.model.User;
import com.cash.user.util.UserCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        User user = UserCreator.createUser();
        userRepository.save(user);
    }

    @Test
    void findUserDTOById_WhenIdExists_ReturnUserBalanceDTO() {
        User userMock = UserCreator.createUserSave();

        Optional<UserBalanceDTO> userBalanceDTO = userRepository.findUserDTOById(1L);

        assertNotNull(userBalanceDTO.get());
        assertEquals(userMock.getCpfCnpj(), userBalanceDTO.get().cpfCnpj());
    }

    @Test
    void findUserDTOById_WhenIdDoesNotExists_ReturnEmpty() {
        Optional<UserBalanceDTO> userBalanceDTO = userRepository.findUserDTOById(3L);

        assertTrue(userBalanceDTO.isEmpty());
    }

    @Test
    void updateBalance_WhenCalled_ShouldUpdateUserBalance() {
        User userMock = UserCreator.createUser();

        userRepository.updateBalance(userMock.getId(), 501.0);
        entityManager.clear();

        User updatedUser = userRepository.findById(userMock.getId()).orElseThrow();
        assertNotEquals(userMock.getBalance(), updatedUser.getBalance());
        assertEquals(501.0, updatedUser.getBalance());
    }

    @Test
    void getUserByEmail_WhenEmailExists_ReturnLoginDTO() {
        User userMock = UserCreator.createUserSave();

        Optional<LoginDTO> login = userRepository.getUserByEmail(userMock.getContact().email());

        assertNotNull(login.get());
        assertEquals(userMock.getContact().email(), login.get().username());
    }

    @Test
    void getUserByEmail_WhenEmailDoesNotExists_ReturnEmpty() {
        Optional<LoginDTO> login = userRepository.getUserByEmail("email@email.com");

        assertTrue(login.isEmpty());
    }
}
