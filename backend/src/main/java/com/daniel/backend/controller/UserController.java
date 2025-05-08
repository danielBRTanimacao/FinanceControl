package com.daniel.backend.controller;

import com.daniel.backend.DTO.LoginRequestDTO;
import com.daniel.backend.DTO.RegisterRequestDTO;
import com.daniel.backend.service.UserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo() {
        return userService.getUserInfos();
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterRequestDTO entity) {
        return userService.createUser(entity);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestDTO entity) {
        return userService.loginUser(entity);
    }
}
