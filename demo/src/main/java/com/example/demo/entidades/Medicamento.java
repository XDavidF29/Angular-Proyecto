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
import lombok.Data;
import lombok.NoArgsConstructor;

//Medicamentos

@Entity
@Table(name = "MEDICAMENTOS_TABLE")
@Data
@NoArgsConstructor
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
    
        
}