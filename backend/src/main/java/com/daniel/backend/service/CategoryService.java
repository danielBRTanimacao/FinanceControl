package com.daniel.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daniel.backend.entity.CategoryEntity;
import com.daniel.backend.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepo = categoryRepository;
    }

    public List<CategoryEntity> listCategories() {
        return categoryRepo.findAll();
    }

    public CategoryEntity createCategory(CategoryEntity category) {
        return categoryRepo.save(category);
    }
}
