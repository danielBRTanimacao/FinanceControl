package com.daniel.backend.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<CategoryEntity> updateCategory(Long id, CategoryEntity entity) {
        return categoryRepo.findById(id).map(exists -> {
            exists.setName(entity.getName());
            CategoryEntity updated = categoryRepo.save(exists);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<CategoryEntity> deleteCategory(Long id) {
        if (categoryRepo.existsById(id)) {
            categoryRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
