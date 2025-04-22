package com.daniel.backend.infra.security;

import org.springframework.stereotype.Component;

import com.daniel.backend.entity.UserEntity;
import com.daniel.backend.repository.UserRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
