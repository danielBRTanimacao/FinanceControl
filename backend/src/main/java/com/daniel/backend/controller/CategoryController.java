package com.daniel.backend.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.daniel.backend.entity.CategoryEntity;
import com.daniel.backend.service.CategoryService;

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
    public CategoryEntity createCategories(@Validated @RequestBody CategoryEntity category) {
        return categoryServ.createCategory(category);
    }
}
