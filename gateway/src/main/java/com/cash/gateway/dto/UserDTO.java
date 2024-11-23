package com.cash.gateway.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull @NotEmpty
    private String username;
    @NotNull
    @ToString.Exclude
    private String password;
    @NotNull
    @Builder.Default
    private String role = "USER";

    public UserDTO(@NotNull UserDTO user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }
}
