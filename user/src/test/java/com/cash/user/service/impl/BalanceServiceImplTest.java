package com.cash.user.service.impl;

import com.cash.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class BalanceServiceImplTest {

    @InjectMocks
    private BalanceServiceImpl balanceService;
    @Mock
    private UserRepository userRepository;

    @Test
    void updateBalance_WhenValideMessage_ShouldUpdateBalance() throws JsonProcessingException {
        String message = """
        {
          "users": [
            {"userId": 3, "name": "teste", "cpfCnpj": "123456789","balance": 100.0}
          ]
        }
        """;

        balanceService.updateBalance(message);

        verify(userRepository, times(1)).updateBalance(3L, 100.00);
    }

    @Test
    void updateBalance_WhenInvalidMessage_ShouldUpdateBalance() {
        String message = """
        {
          "users": [
            {"id": 3, "username": "teste", "price": 100.0}
          ]
        }
        """;

        assertThrows(JsonProcessingException.class, () -> balanceService.updateBalance(message));
    }

    @Test
    void updateBalance_WhenNoUsers_ShouldNotCallUpdateBalance() throws JsonProcessingException {
        String message = """
        {
          "users": []
        }
        """;

        balanceService.updateBalance(message);

        verify(userRepository, never()).updateBalance(anyLong(), anyDouble());
    }
}
