package com.example.demo.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "TRATAMIENTO_TABLE")
@Data
@NoArgsConstructor
public class Tratamiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date fecha;
    private float precio;

    @ManyToOne
    @JoinColumn(name = "mascota_id")  // La clave foránea en la tabla Tratamiento que referencia a Mascota
    private Mascota mascota;

    @ManyToOne // Relación con veterinario
    @JoinColumn(name = "veterinario_id")  // Nombre único para esta clave foránea
    private Veterinario veterinario;

    @ManyToMany // Relación con medicamentos
    @JoinTable(
        name = "tratamiento_medicamento",
        joinColumns = @JoinColumn(name = "tratamiento_id"),
        inverseJoinColumns = @JoinColumn(name = "medicamento_id")
    )
    private List<Medicamento> medicamentos = new ArrayList<>();

    
    public Tratamiento(Integer id,Date fecha, float precio, Mascota mascota, Veterinario veterinario,
            List<Medicamento> medicamentos) {
        this.id = id;
        this.fecha = fecha;
        this.precio = precio;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.medicamentos = medicamentos;
    }

    public Tratamiento( Date fecha, float precio, Mascota mascota, Veterinario veterinario,
            List<Medicamento> medicamentos) {
        
        this.fecha = fecha;
        this.precio = precio;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.medicamentos = medicamentos;
    }


}
