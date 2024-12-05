package com.cash.user.controller;

import com.cash.user.config.TestSecurityConfig;
import com.cash.user.dto.LoginDTO;
import com.cash.user.dto.UserBalanceDTO;
import com.cash.user.dto.UserDTO;
import com.cash.user.model.User;
import com.cash.user.repository.UserRepository;
import com.cash.user.util.UserCreator;
import com.cash.user.util.UserDTOCreator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ImportAutoConfiguration(exclude = {KafkaAutoConfiguration.class})
@Import(TestSecurityConfig.class)
@AutoConfigureTestDatabase
class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void getUsers_WhenCalled_ShouldReturnUsers() {
        User user = UserCreator.createUser();
        userRepository.save(user);

        User[] forObject = restTemplate.getForObject("/all", User[].class);

        assertNotNull(forObject);
        assertTrue(forObject.length > 0);
        assertEquals(user.getCpfCnpj(), forObject[0].getCpfCnpj());
    }

    @Test
    void getUsers_WhenNoUsers_ShouldReturnEmpty() {
        User[] forObject = restTemplate.getForObject("/all", User[].class);

        assertNotNull(forObject);
        assertEquals(0, forObject.length);
    }

    @Test
    void getUsers_WhenIdExist_ShouldReturnUser() {
        User user = UserCreator.createUserSave();
        User savedUser = userRepository.save(user);

        UserBalanceDTO forObject = restTemplate.getForObject("/" + savedUser.getId(), UserBalanceDTO.class);

        assertNotNull(forObject);
        assertEquals(user.getName(), forObject.name());
        assertEquals(user.getCpfCnpj(), forObject.cpfCnpj());
    }

    @Test
    void getUsers_WhenIdDoesNotExists_ShouldReturnNull() {
        UserBalanceDTO forObject = restTemplate.getForObject("/" + anyLong(), UserBalanceDTO.class);

        assertNull(forObject);
    }

    @Test
    void addUser_WhenUserDTO_ShouldCreateUser() {
        UserDTO userDTO = UserDTOCreator.createUserDTO();

        ResponseEntity<User> response = restTemplate.postForEntity("/create", userDTO, User.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void getUserByEmail_WhenEmailExists_ShouldReturnUser() {
        User user = UserCreator.createUser();
        userRepository.save(user);

        ResponseEntity<LoginDTO> responseEntity = restTemplate.getForEntity("/login/" + user.getContact().email(), LoginDTO.class);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertNotNull(responseEntity.getBody());
        assertEquals(user.getContact().email(), responseEntity.getBody().username());
    }
}
