package com.daniel.backend.DTO;

import jakarta.validation.constraints.*;

public record LoginRequestDTO(
    @Email(message = "Email inválido!")
    @NotBlank(message = "email não pode estar vazio!")
    String email,
    
    @NotBlank(message = "password não pode estar vazio!")
    @Size(min = 6, message = "password deve ter no mínimo 6 caracteres")
    String password
)
    {}