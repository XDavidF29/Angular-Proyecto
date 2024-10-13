package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Medicamento;
import com.example.demo.repositorio.MedicamentoRepository;
import com.example.demo.servicio.MedicamentoService;

@RestController
@RequestMapping("/medicamento")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicamentoController {

    @Autowired
    MedicamentoService service;

    @Autowired
    MedicamentoRepository medicamentoRepository;

    // Obtener todos los medicamentos
    @GetMapping("/all")
    public List<Medicamento> mostrarMedicamentos(Model model) {
        return service.searchAll();
    }

    // Obtener un medicamento por ID
    @GetMapping("/find")
    public Medicamento mostrarMedicamento(Model model, @RequestParam("id") Long idMedicamento) {
        return service.searchById(idMedicamento);
    }

    // Agregar un medicamento
    @PostMapping("/add")
    public void agregarMedicamento(@RequestBody Medicamento medicamento) {
        service.add(medicamento);
    }

    // Actualizar un medicamento
    @PutMapping("/update/{id}")
    public void updateMedicamento(@PathVariable("id") Long idMedicamento, @ModelAttribute("medicamento") Medicamento medicamento) {
        medicamento.setId(idMedicamento);
        service.update(medicamento);
    }

    // Eliminar un medicamento
    @DeleteMapping("/delete/{id}")
    public void eliminarMedicamento(@PathVariable("id") Long idMedicamento) {
        service.deleteById(idMedicamento);
    }
}
