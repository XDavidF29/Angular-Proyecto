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
@Table(name = "USUARIOS_TABLE")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String correo;
    private int celular;
    private int cedula;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Mascota> mascotas;


    // Constructor vacío
    public Usuario() {
    
    }

    // Constructor con todos los campos
    public Usuario(Integer id, String nombre, String correo, int celular, int cedula, List<Mascota> mascotas) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.celular = celular;
        this.cedula = cedula;
        this.mascotas = mascotas != null ? mascotas : new ArrayList<>();  // Asegura que mascotas no sea null
    }

    public Usuario(String nombre, String correo, int celular, int cedula, List<Mascota> mascotas) {
        this.nombre = nombre;
        this.correo = correo;
        this.celular = celular;
        this.cedula = cedula;
        this.mascotas = mascotas != null ? mascotas : new ArrayList<>();  // Asegura que mascotas no sea null
    }

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public List<Mascota> getMascotas() {
        if (mascotas == null) {
            mascotas = new ArrayList<>();  // Inicializa la lista si es null
        }
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public void addMascota(Mascota mascota) {
        mascotas.add(mascota);
        mascota.setUsuario(this);
    }

    public void removeMascota(Mascota mascota) {
        mascotas.remove(mascota);
        mascota.setUsuario(null);
    }
}
