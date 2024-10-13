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

import com.example.demo.entidades.Tratamiento;
import com.example.demo.servicio.TratamientoService;

@RestController
@RequestMapping("/tratamiento")
@CrossOrigin(origins = "http://localhost:4200")
public class TratamientoController {

    @Autowired
    TratamientoService service;

    // Obtener todos los tratamientos
    @GetMapping("/all")
    public List<Tratamiento> mostrarTratamientos(Model model) {
        return service.searchAll();
    }

    // Obtener un tratamiento por ID
    @GetMapping("/find")
    public Tratamiento mostrarTratamiento(Model model, @RequestParam("id") Integer idTratamiento) {
        return service.searchById(idTratamiento);
    }

    // Agregar un tratamiento
    @PostMapping("/add")
    public void agregarTratamiento(@RequestBody Tratamiento tratamiento) {
        service.add(tratamiento);
    }

    // Actualizar un tratamiento
    @PutMapping("/update/{id}")
    public void updateTratamiento(@PathVariable("id") Integer idTratamiento, @ModelAttribute("tratamiento") Tratamiento tratamiento) {
        tratamiento.setId(idTratamiento);
        service.update(tratamiento);
    }

    // Eliminar un tratamiento
    @DeleteMapping("/delete/{id}")
    public void eliminarTratamiento(@PathVariable("id") Integer idTratamiento) {
        service.deleteById(idTratamiento);
    }
}
