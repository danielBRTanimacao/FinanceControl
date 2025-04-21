package com.daniel.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name não pode estar vazio!")
    private String name;

    @Email(message = "Email invalido!")
    @NotBlank(message = "email não pode estar vazio!")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "password não pode estar vazio!")
    @Size(min = 6, message = "password deve ser maior de 6 caracteres")
    private String password;
}
