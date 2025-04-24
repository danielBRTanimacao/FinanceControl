package com.daniel.backend.service;


import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daniel.backend.DTO.*;
import com.daniel.backend.entity.UserEntity;
import com.daniel.backend.infra.security.TokenService;
import com.daniel.backend.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserRepository userRepo;

    @SuppressWarnings("rawtypes")
    public ResponseEntity createUser(@Valid RegisterRequestDTO entity) {
        Optional<UserEntity> user = userRepo.findByEmail(entity.email());

        if(user.isEmpty()) {
            UserEntity newUser = new UserEntity();
            newUser.setPassword(passwordEncoder.encode(entity.password()));
            newUser.setEmail(entity.email());
            newUser.setName(entity.name());
            userRepo.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity loginUser(LoginRequestDTO entity) {   
        UserEntity user = userRepo.findByEmail(entity.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(entity.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
