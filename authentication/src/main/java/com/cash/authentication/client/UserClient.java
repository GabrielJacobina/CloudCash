package com.cash.authentication.client;

import com.cash.authentication.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "${api.user}")
public interface UserClient {

    @GetMapping
    UserDTO getUserByUsername(String username);
}
