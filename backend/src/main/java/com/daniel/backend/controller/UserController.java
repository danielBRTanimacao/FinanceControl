package com.daniel.backend.controller;

import com.daniel.backend.DTO.LoginRequestDTO;
import com.daniel.backend.DTO.RegisterRequestDTO;
import com.daniel.backend.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @SuppressWarnings("rawtypes")
    @PostMapping("/me")
    public ResponseEntity infosUser() {
        return userService.getUserInfos();
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity createUserEntity(@RequestBody RegisterRequestDTO entity) {
        return userService.createUser(entity);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity loginUserEntity(@RequestBody LoginRequestDTO entity) {
        return userService.loginUser(entity);
    }
}
