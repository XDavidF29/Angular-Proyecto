package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidades.Tratamiento;

public interface TratamientoService {
    public Tratamiento searchById(Integer id);
    public List<Tratamiento> searchAll();
    public void deleteById(Integer id);
    public void update(Tratamiento tratamiento);
    public void add(Tratamiento tratamiento);
}
