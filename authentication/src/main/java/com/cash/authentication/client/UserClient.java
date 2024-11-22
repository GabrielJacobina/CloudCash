package com.cash.authentication.client;

import com.cash.authentication.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "user", url = "user/user")
@FeignClient("user" + "/user")
public interface UserClient {

    @GetMapping("/login/{username}")
    UserDTO getUserByUsername(@PathVariable String username);
}
