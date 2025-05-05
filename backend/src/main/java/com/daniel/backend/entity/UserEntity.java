package com.daniel.backend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TransactionEntity> transactions;

    @NotBlank(message = "name não pode estar vazio!")
    @Size(min = 2, max = 50, message = "Name tem que ter no mínimo 2 caracteres e no máximo 50")
    @Column(nullable = false)
    private String name;

    @Email(message = "Email inválido!")
    @NotBlank(message = "email não pode estar vazio!")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "password não pode estar vazio!")
    @Size(min = 6, message = "password deve ter no mínimo 6 caracteres")
    @Column(nullable = false)
    private String password;
}

