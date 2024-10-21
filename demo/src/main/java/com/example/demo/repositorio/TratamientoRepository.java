package com.example.demo.repositorio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entidades.Tratamiento;

import jakarta.transaction.Transactional;
import com.example.demo.entidades.Veterinario;


public interface TratamientoRepository extends JpaRepository<Tratamiento, Integer> {

    List<Tratamiento> findByVeterinarioId(Integer id);

     // Eliminar todos los tratamientos asociados a una mascota por su id
    @Modifying
    @Transactional
    @Query("DELETE FROM Tratamiento t WHERE t.mascota.id = :mascotaId")
    void deleteByMascotaId(Long mascotaId);

    @Query("SELECT t.fecha, COUNT(t) FROM Tratamiento t GROUP BY t.fecha")
    List<Object[]> obtenerTratamientosPorFecha();

    @Query("SELECT COUNT(t) FROM Tratamiento t WHERE t.fecha >= :fechaInicio")
    Long contarTratamientosUltimoMes(@Param("fechaInicio") java.sql.Date fechaInicio);

    @Query("SELECT m.nombre, COUNT(t) FROM Tratamiento t JOIN t.medicamentos m WHERE t.fecha >= :fechaInicio GROUP BY m.nombre")
    List<Object[]> contarTratamientosPorMedicamentoUltimoMes(@Param("fechaInicio") java.sql.Date fechaInicio);

    @Query("SELECT SUM(t.precio) FROM Tratamiento t")
    Double calcularGananciasTotales();

    @Query("SELECT t, SUM(m.unidadesVendidas) as total_vendido FROM Tratamiento t JOIN t.medicamentos m GROUP BY t ORDER BY total_vendido DESC")
    List<Object[]> obtenerTop3Tratamientos();



}
