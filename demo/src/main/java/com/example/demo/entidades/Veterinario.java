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
import jakarta.persistence.Table;

@Entity
@Table(name = "VETERINARIOS_TABLE")
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cedula;
    private String password;
    private String especialidad;
    private int atenciones;
    private String nombre;
    private String foto;

    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL, orphanRemoval = true)  // Relación con Tratamiento
    @JsonIgnore
    private List<Tratamiento> tratamientos = new ArrayList<>();

    // Constructor vacío
    public Veterinario() {
    }

    // Constructor con todos los campos
    public Veterinario(Long id, String cedula, String password, String especialidad, int atenciones, String nombre,
                       String foto, List<Tratamiento> tratamientos) {
        this.id = id;
        this.cedula = cedula;
        this.password = password;
        this.especialidad = especialidad;
        this.atenciones = atenciones;
        this.nombre = nombre;
        this.foto = foto;
        this.tratamientos = tratamientos != null ? tratamientos : new ArrayList<>();
    }

    public Veterinario(String cedula, String password, String especialidad, int atenciones, String nombre, String foto,
                       List<Tratamiento> tratamientos) {
        this.cedula = cedula;
        this.password = password;
        this.especialidad = especialidad;
        this.atenciones = atenciones;
        this.nombre = nombre;
        this.foto = foto;
        this.tratamientos = tratamientos != null ? tratamientos : new ArrayList<>();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getAtenciones() {
        return atenciones;
    }

    public void setAtenciones(int atenciones) {
        this.atenciones = atenciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Tratamiento> getTratamientos() {
        if (tratamientos == null) {
            tratamientos = new ArrayList<>();
        }
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
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
