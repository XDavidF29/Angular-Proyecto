package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidades.Veterinario;

@SuppressWarnings("unused")
public interface VeterinarioService {
    public Veterinario searchById(Long id);
    public List<Veterinario> searchAll();
    public void deleteById(Long id);
    public Veterinario update(Veterinario veterinario);
    public void add(Veterinario veterinario);
    public boolean verificarCredenciales(String cedula);
    public Veterinario searchByCedula(String cedula);
    public List<Veterinario> buscarPorNombre(String nombre);
}
