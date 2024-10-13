package com.example.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Tratamiento;
import com.example.demo.repositorio.TratamientoRepository;

@Service
public class TratamientoServiceImpl implements TratamientoService {

    @Autowired
    TratamientoRepository repo;

    @Override
    public Tratamiento searchById(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public List<Tratamiento> searchAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public void update(Tratamiento tratamiento) {
        repo.save(tratamiento);
    }

    @Override
    public void add(Tratamiento tratamiento) {
        repo.save(tratamiento);
    }
}