package com.cash.user.dto;

import com.cash.user.enums.UserTypeEnum;
import com.cash.user.model.Contact;

public record UserDTO(String name,
                      String cpfCnpj,
                      Contact contact,
                      UserTypeEnum userType,
                      double balance) {
}
