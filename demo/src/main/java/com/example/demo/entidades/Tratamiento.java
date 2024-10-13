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


@Entity
@Table(name = "TRATAMIENTO_TABLE")
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

    // Constructor vacío
    public Tratamiento() {}

    public Tratamiento(Date fecha, float precio, Mascota mascota, Veterinario veterinario,
            List<Medicamento> medicamentos) {
        this.fecha = fecha;
        this.precio = precio;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.medicamentos = medicamentos;
    }

    public Tratamiento(Integer id, Date fecha, float precio, Mascota mascota, Veterinario veterinario,
            List<Medicamento> medicamentos) {
        this.id = id;
        this.fecha = fecha;
        this.precio = precio;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.medicamentos = medicamentos;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    // Constructor con todos los parámetros
    

}
