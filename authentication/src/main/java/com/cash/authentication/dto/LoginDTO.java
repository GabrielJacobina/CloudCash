package com.cash.authentication.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginDTO(@NotNull @NotEmpty String username, @NotNull @NotEmpty String password) {
}