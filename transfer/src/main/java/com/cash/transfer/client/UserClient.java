package com.cash.transfer.client;

import com.cash.transfer.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user" + "/user")
public interface UserClient {

    @GetMapping("/{id}")
    User getUser(@PathVariable Long id);
}
