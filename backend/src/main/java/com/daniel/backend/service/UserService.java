package com.daniel.backend.service;


import org.springframework.stereotype.Service;

import com.daniel.backend.entity.UserEntity;
import com.daniel.backend.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository UserRepository) {
        this.userRepo = UserRepository;
    }

    public UserEntity getInfos(Long id) {   
        return userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não existe"));
    }

    public UserEntity createUser(@Valid UserEntity entity) {   
        return userRepo.save(entity);
    }
}
