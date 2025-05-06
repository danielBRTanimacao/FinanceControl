package com.daniel.backend.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daniel.backend.DTO.*;
import com.daniel.backend.entity.UserEntity;
import com.daniel.backend.infra.security.TokenService;
import com.daniel.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserRepository userRepo;

    public ResponseEntity<?> getUserInfos() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("Usuário não autenticado");
        }

        var principal = authentication.getPrincipal();
        String username = principal instanceof UserDetails userDetails ? userDetails.getUsername() : principal.toString();

        return ResponseEntity.ok(new ResponseDTO(username, username));
    }

    public ResponseEntity<?> createUser(RegisterRequestDTO entity) {
        Optional<UserEntity> user = userRepo.findByEmail(entity.email());

        if (user.isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já existe com este email");
        }

        UserEntity newUser = new UserEntity();

        newUser.setPassword(passwordEncoder.encode(entity.password()));
        newUser.setEmail(entity.email());
        newUser.setName(entity.name());
        userRepo.save(newUser);

        String token = tokenService.generateToken(newUser);
        return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
    }

    public ResponseEntity<?> loginUser(LoginRequestDTO entity) {
        UserEntity user = userRepo.findByEmail(entity.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (passwordEncoder.matches(entity.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }

        return ResponseEntity.badRequest().body("Credenciais inválidas");
    }
}
