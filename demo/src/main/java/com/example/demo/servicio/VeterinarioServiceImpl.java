package com.example.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.VeterinarioRepository;

@Service    
public class VeterinarioServiceImpl implements VeterinarioService{
    @Autowired
    VeterinarioRepository repo;

    @Override
    public Veterinario searchById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public List<Veterinario> searchAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        repo.deleteById(id);
    }

    @Override
    public void update(Veterinario veterinario) {
        // TODO Auto-generated method stub
        repo.save(veterinario);
    }

    @Override
    public void add(Veterinario veterinario) {
        // TODO Auto-generated method stub
        repo.save(veterinario);
    }

    
}
