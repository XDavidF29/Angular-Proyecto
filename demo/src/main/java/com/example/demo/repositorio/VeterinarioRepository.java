package com.example.demo.repositorio;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Usuario;
import com.example.demo.entidades.Veterinario;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long>{
    
    @Query("SELECT v.nombre, COUNT(t) FROM Veterinario v JOIN v.tratamientos t GROUP BY v.id")
    List<Object[]> obtenerAtencionesPorVeterinario();

    Veterinario findByCedula(String cedula);

    @Query("SELECT m FROM Veterinario m WHERE LOWER(m.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Veterinario> buscarPorNombre(String nombre);

}
