package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidades.Veterinario;

@SuppressWarnings("unused")
public interface VeterinarioService {
    public Veterinario searchById(Long id);
    public List<Veterinario> searchAll();
    public void deleteById(Long id);
    public void update(Veterinario veterinario);
    public void add(Veterinario veterinario);
    
}
