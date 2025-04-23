package com.daniel.backend.service;


import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daniel.backend.entity.UserEntity;
import com.daniel.backend.infra.security.TokenService;
import com.daniel.backend.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserRepository userRepo;

    public UserService(UserRepository UserRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.tokenService = tokenService;
        this.userRepo = UserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity getInfos(Long id) {   
        return userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não existe"));
    }

    public ResponseEntity<Optional<UserEntity>> createUser(@Valid UserEntity entity) {
        Optional<UserEntity> user = userRepo.findByEmail(entity.getEmail());

        if(user.isEmpty()) {
            UserEntity newUser = new UserEntity();
            newUser.setPassword(passwordEncoder.encode(entity.getPassword()));
            newUser.setEmail(entity.getEmail());
            newUser.setName(entity.getName());
            userRepo.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<UserEntity> loginUser(UserEntity entity) {   
        UserEntity user = userRepo.findByEmail(entity.getEmail()).orElseThrow(
            () -> new RuntimeException("Usuario não encontrado!")
        );
        if (passwordEncoder.matches(entity.getPassword(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }
}
