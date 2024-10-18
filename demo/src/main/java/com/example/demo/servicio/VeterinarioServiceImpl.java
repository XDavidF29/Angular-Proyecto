package com.example.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Usuario;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.VeterinarioRepository;

@Service    
public class VeterinarioServiceImpl implements VeterinarioService {
    
    @Autowired
    private VeterinarioRepository repo;

    @Override
    public Veterinario searchById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));
    }

    @Override
    public List<Veterinario> searchAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Veterinario update(Veterinario veterinario) {
        
        return repo.save(veterinario); // Guardar y devolver el veterinario actualizado
    }

    @Override
    public void add(Veterinario veterinario) {
        repo.save(veterinario);
    }

    @Override
    public boolean verificarCredenciales(String cedula) {
        Veterinario veterinario = repo.findByCedula(cedula);
        return veterinario != null && veterinario.getCedula().equals(cedula);
    }

    @Override
    public Veterinario searchByCedula(String cedula) {
        return repo.findByCedula(cedula);
    }

    @Override
    public List<Veterinario> buscarPorNombre(String nombre) {
        return repo.buscarPorNombre(nombre);
    }
}
