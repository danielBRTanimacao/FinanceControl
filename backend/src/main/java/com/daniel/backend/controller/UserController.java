package com.daniel.backend.controller;

import com.daniel.backend.entity.UserEntity;
import com.daniel.backend.service.UserService;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me/{id}")
    public UserEntity getUserInfos(@PathVariable Long id) {
        return userService.getInfos(id);
    }
    

    @PostMapping("/register")
    public ResponseEntity<Optional<UserEntity>> createUserEntity(@RequestBody UserEntity entity) {
        return userService.createUser(entity);
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> loginUserEntity(@RequestBody UserEntity entity) {
        return userService.loginUser(entity);
    }
}
