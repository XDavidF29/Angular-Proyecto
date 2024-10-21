package com.example.demo.entidades;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

//Medicamentos

@Entity
@Table(name = "MEDICAMENTOS_TABLE")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private double precio_compra;
    private double  precio_venta;
    private int unidadesVendidas;
    private int unidadesDisponibles;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "medicamentos")  // Relación muchos a muchos bidireccional con Tratamiento
    private List<Tratamiento> tratamientos = new ArrayList<>();
    
    //Constructor Vacio
    public Medicamento() {
    }

    //Constructor
    public Medicamento(Long id, String nombre, float precio_compra, float precio_venta, int unidadesVendidas,
            int unidadesDisponibles, List<Tratamiento> tratamientos) {
        this.id = id;
        this.nombre = nombre;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.unidadesVendidas = unidadesVendidas;
        this.unidadesDisponibles = unidadesDisponibles;
        this.tratamientos = tratamientos;
    }

    //Constructor sin ID
    public Medicamento(String nombre, float precio_compra, float precio_venta, int unidadesVendidas,
            int unidadesDisponibles, List<Tratamiento> tratamientos) {
        this.nombre = nombre;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.unidadesVendidas = unidadesVendidas;
        this.unidadesDisponibles = unidadesDisponibles;
        this.tratamientos = tratamientos;
    }
    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(int unidades_vendidas) {
        this.unidadesVendidas = unidades_vendidas;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidades_disponibles) {
        this.unidadesDisponibles = unidades_disponibles;
    }

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }
        
}