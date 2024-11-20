package com.cash.authentication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    @NotNull
    private Long id;
    @NotNull @NotEmpty
    private String username;
    @NotNull @NotEmpty
    @ToString.Exclude
    private String password;
    @NotNull @NotEmpty
    private String role;

    public UserDTO(@NotNull UserDTO user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }
}
