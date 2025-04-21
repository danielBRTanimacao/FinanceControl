package com.daniel.backend.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "Categoria é obrigatoria")
    @Valid
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "\"value\"")
    private BigDecimal value;

    private LocalDate earnedDate;

    private final LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updated_Date = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        if (earnedDate == null) {
            earnedDate = LocalDate.now();
        }
    }
}
