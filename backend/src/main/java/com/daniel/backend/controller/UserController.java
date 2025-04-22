package com.daniel.backend.controller;

import com.daniel.backend.entity.UserEntity;
import com.daniel.backend.service.UserService;

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
    public UserEntity createUserEntity(@RequestBody UserEntity entity) {
        return userService.createUser(entity);
    }
    
}
