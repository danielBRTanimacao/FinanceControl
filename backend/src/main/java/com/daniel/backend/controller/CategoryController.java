package com.daniel.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.daniel.backend.entity.CategoryEntity;
import com.daniel.backend.service.CategoryService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    private final CategoryService categoryServ;

    public CategoryController(CategoryService categoryService) {
        this.categoryServ = categoryService;
    }

    @GetMapping
    public List<CategoryEntity> getAllCategories() {
        return categoryServ.listCategories();
    }

    @PostMapping
    public ResponseEntity<CategoryEntity> createCategory(@Valid @RequestBody CategoryEntity category) {
        CategoryEntity saved = categoryServ.createCategory(category);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryEntity entity) {
        return categoryServ.updateCategory(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryEntity> deleteCategory(@Valid @PathVariable Long id) {
        return categoryServ.deleteCategory(id);
    }
}

