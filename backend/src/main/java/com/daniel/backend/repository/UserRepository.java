package com.daniel.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.backend.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    Object findByEmail(String login);
}
