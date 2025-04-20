package com.daniel.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.daniel.backend.entity.CategoryEntity;
import com.daniel.backend.service.CategoryService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class CategoryController {
    
    private final CategoryService categoryServ;

    public CategoryController(CategoryService categoryService) {
        this.categoryServ = categoryService;
    }

    @GetMapping("/categories")
    public List<CategoryEntity> getAllCategories() {
        return categoryServ.listCategories();
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryEntity> createCategories(@Valid @RequestBody CategoryEntity category) {
        CategoryEntity saved = categoryServ.createCategory(category);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryEntity> updateSpecificCategory(@PathVariable Long id, @RequestBody CategoryEntity entity) {
        return categoryServ.updateCategory(id, entity);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<CategoryEntity> delSpecificCategory(@PathVariable Long id ) {
        return categoryServ.deleteCategory(id);
    }
}
