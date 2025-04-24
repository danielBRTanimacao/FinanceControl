package com.daniel.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.backend.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{}