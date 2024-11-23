package com.cash.user.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final DiscoveryClient discoveryClient;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().access(eurekaBasedAuthorizationManager()))
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public AuthorizationManager<RequestAuthorizationContext> eurekaBasedAuthorizationManager() {
        return (authentication, context) -> {
            HttpServletRequest request = context.getRequest();
            String remoteHost = request.getRemoteHost();

            boolean isAuthorized = discoveryClient.getInstances("gateway").stream()
                    .anyMatch(instance -> instance.getHost().equals(remoteHost));

            return new AuthorizationDecision(isAuthorized);
        };
    }
}
