package com.daniel.backend.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.daniel.backend.exceptions.InvalidCredencialsException;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull(message = "category é obrigatória")
    private CategoryEntity category;

    @NotNull(message = "user é obrigatório")
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity user;

    @NotBlank(message = "title não pode ser vazio!")
    @Size(min=2, max=100)
    private String title;

    @NotNull(message = "value é obrigatório")
    @Digits(integer = 10, fraction = 2, message = "O valor deve ter no máximo 10 digitos inteiros e 2 decimais")
    @Column(name = "\"value\"")
    private BigDecimal value;

    private LocalDate earnedDate;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist
    public void prePersist() {
        if (earnedDate == null) {
            earnedDate = LocalDate.now();
        }
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
        updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
