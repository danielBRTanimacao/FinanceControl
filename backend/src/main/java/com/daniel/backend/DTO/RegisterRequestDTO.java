package com.daniel.backend.DTO;

import jakarta.validation.constraints.*;

public record RegisterRequestDTO(
    @NotBlank(message = "name não pode ser vazio!")
    @NotNull
    @Size(min=2, max=30)
    String name, 

    @Email(message = "Email inválido!")
    @NotBlank(message = "email não pode estar vazio!")
    String email,
    
    @NotBlank(message = "password não pode estar vazio!")
    @Size(min = 6, message = "password deve ter no mínimo 6 caracteres")
    String password
) {}
