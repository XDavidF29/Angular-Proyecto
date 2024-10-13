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

import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.VeterinarioRepository;
import com.example.demo.servicio.VeterinarioService;

@RestController
@RequestMapping("/veterinario")
@CrossOrigin(origins = "http://localhost:4200")
public class VeterinarioController {

    @Autowired
    VeterinarioService service;

    @Autowired
    VeterinarioRepository veterinarioRepository;

    // Obtener todos los veterinarios
    @GetMapping("/all")
    public List<Veterinario> mostrarVeterinarios(Model model) {
        return service.searchAll();
    }

    // Obtener un veterinario por ID
    @GetMapping("/find")
    public Veterinario mostrarVeterinario(Model model, @RequestParam("id") Long idVeterinario) {
        return service.searchById(idVeterinario);
    }

    // Agregar un veterinario
    @PostMapping("/add")
    public void agregarVeterinario(@RequestBody Veterinario veterinario) {
        service.add(veterinario);
    }

    // Actualizar un veterinario
    @PutMapping("/update/{id}")
    public void updateVeterinario(@PathVariable("id") Long idVeterinario, @ModelAttribute("veterinario") Veterinario veterinario) {
        veterinario.setId(idVeterinario);
        service.update(veterinario);
    }

    // Eliminar un veterinario
    @DeleteMapping("/delete/{id}")
    public void eliminarVeterinario(@PathVariable("id") Long idVeterinario) {
        service.deleteById(idVeterinario);
    }
}
