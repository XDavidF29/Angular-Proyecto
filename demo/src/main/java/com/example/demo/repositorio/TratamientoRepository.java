package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entidades.Tratamiento;

import jakarta.transaction.Transactional;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Integer> {

     // Eliminar todos los tratamientos asociados a una mascota por su id
    @Modifying
    @Transactional
    @Query("DELETE FROM Tratamiento t WHERE t.mascota.id = :mascotaId")
    void deleteByMascotaId(Long mascotaId);

}
