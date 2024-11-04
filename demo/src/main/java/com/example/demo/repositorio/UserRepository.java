package com.example.demo.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.demo.entidades.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
    
}
