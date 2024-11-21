package com.cash.authentication.config;

import com.cash.authentication.config.jwt.JwtConfiguration;
import com.cash.authentication.config.jwt.filter.JwtTokenAuthorizationFilter;
import com.cash.authentication.config.jwt.filter.JwtUsernameAndPasswordAuthenticationFilter;
import com.cash.authentication.config.jwt.token.TokenConverter;
import com.cash.authentication.config.jwt.token.TokenCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtConfiguration jwtConfiguration;
    private final TokenCreator tokenCreator;
    private final TokenConverter tokenConverter;
    private final AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**").permitAll()
                        .anyRequest().authenticated())
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager, jwtConfiguration, tokenCreator))
                .addFilterAfter(new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
