package com.cash.payment.client;

import com.cash.payment.DTO.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user", url = "${api.user}")
public interface UserClient {

    @GetMapping("/{id}")
    User getUser(@PathVariable Long id);
}
