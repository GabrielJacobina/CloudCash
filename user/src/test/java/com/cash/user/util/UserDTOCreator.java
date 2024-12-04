package com.cash.user.util;

import com.cash.user.dto.UserDTO;
import com.cash.user.enums.UserTypeEnum;

public class UserDTOCreator {

    public static UserDTO createUserDTO() {
        return new UserDTO("Fulano", "123456789", ContactCreator.createContact(), UserTypeEnum.INDIVIDUAL, 4525.02);
    }
}
