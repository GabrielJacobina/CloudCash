package com.cash.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.server.mvc.config.GatewayMvcProperties;
import org.springframework.cloud.gateway.server.mvc.config.RouteProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RoutesController {

    private final GatewayMvcProperties properties;

    @GetMapping("/gateway/routes")
    public List<RouteProperties> getRoutes() {
        return properties.getRoutes();
    }
}
