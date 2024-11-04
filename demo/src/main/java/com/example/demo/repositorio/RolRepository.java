package com.example.demo.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    
    Optional<Rol> findByName(String name);
    
}
