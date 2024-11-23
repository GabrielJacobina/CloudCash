package com.cash.gateway.client;

import com.cash.gateway.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user" + "/user")
public interface UserClient {

    @GetMapping("/login/{username}")
    UserDTO getUserByUsername(@PathVariable String username);
}
