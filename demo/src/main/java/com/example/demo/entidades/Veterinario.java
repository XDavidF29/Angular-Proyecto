package com.example.demo.entidades;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VETERINARIOS_TABLE")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Veterinario {


    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cedula;
    private String password;
    private String especialidad;
    private int atenciones;
    private String nombre;
    private String foto;
    private Estado estado;

    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL, orphanRemoval = true)  // Relación con Tratamiento
    @JsonIgnore
    private List<Tratamiento> tratamientos = new ArrayList<>();


    // Constructor con todos los campos
    public Veterinario(Long id, String cedula, String password, String especialidad, int atenciones, String nombre,
                       String foto, ArrayList<Tratamiento> tratamientos, Estado activo) {
        this.id = id;
        this.cedula = cedula;
        this.password = password;
        this.especialidad = especialidad;
        this.atenciones = atenciones;
        this.nombre = nombre;
        this.foto = foto;
        this.tratamientos = tratamientos != null ? tratamientos : new ArrayList<>();
        this.estado = activo;
    }
    // Enumeración para el estado del veterinario
    public enum Estado {
        Activo,
        Inactivo
    }
    
    public Veterinario(String cedula, String password, String especialidad, int atenciones, String nombre, String foto,
                       List<Tratamiento> tratamientos, Estado estado) {
        this.cedula = cedula;
        this.password = password;
        this.especialidad = especialidad;
        this.atenciones = atenciones;
        this.nombre = nombre;
        this.foto = foto;
        this.tratamientos = tratamientos != null ? tratamientos : new ArrayList<>();
        this.estado = estado;
    }


    // Métodos para agregar y remover tratamientos
    public void addTratamiento(Tratamiento tratamiento) {
        tratamientos.add(tratamiento);
        tratamiento.setVeterinario(this);  // Establece la relación bidireccional
    }

    public void removeTratamiento(Tratamiento tratamiento) {
        tratamientos.remove(tratamiento);
        tratamiento.setVeterinario(null);  // Rompe la relación bidireccional
    }
}
