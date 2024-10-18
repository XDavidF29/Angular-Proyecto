package com.example.demo.repositorio;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    List<Mascota> findByUsuarioCedula(Integer cedula);

    @Query("SELECT m.raza, COUNT(m) FROM Mascota m GROUP BY m.raza")
    List<Object[]> obtenerEstadisticasMascotas();

    @Query("SELECT COUNT(m) FROM Mascota m")
    Long contarMascotasTotales();

    @Query("SELECT m FROM Mascota m WHERE LOWER(m.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Mascota> buscarPorNombre(String nombre);

}
