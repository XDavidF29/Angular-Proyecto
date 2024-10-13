package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidades.Medicamento;

public interface MedicamentoService {
    public Medicamento searchById(Long id);
    public List<Medicamento> searchAll();
    public void deleteById(Long id);
    public void update(Medicamento medicamento);
    public void add(Medicamento medicamento);
}
