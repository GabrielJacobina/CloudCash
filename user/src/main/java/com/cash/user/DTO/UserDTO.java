package com.cash.user.DTO;

import com.cash.user.enums.UserTypeEnum;
import com.cash.user.model.Contact;

public record UserDTO(String name,
                      String CPFCNPJ,
                      Contact contact,
                      UserTypeEnum userType,
                      double balance) {
}
