package com.daniel.backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "categories")
@Data
public class CategoryEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name não pode estar vazio")
    @Column(nullable = false)
    private String name;

    public void setName(String name) {
        this.name = name != null ? name.toUpperCase() : null;
    }

    public String getName() {
        return this.name;
    }
}
