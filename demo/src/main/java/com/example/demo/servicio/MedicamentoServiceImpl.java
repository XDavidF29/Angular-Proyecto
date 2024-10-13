package com.example.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Medicamento;
import com.example.demo.repositorio.MedicamentoRepository;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    MedicamentoRepository repo;

    @Override
    public Medicamento searchById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public List<Medicamento> searchAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void update(Medicamento medicamento) {
        repo.save(medicamento);
    }

    @Override
    public void add(Medicamento medicamento) {
        repo.save(medicamento);
    }
}
