package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Usuario;
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
    public List<Veterinario> mostrarVeterinarios() {
        return service.searchAll();
    }

    // Obtener un veterinario por ID
    @GetMapping("/find/{id}")
    public Veterinario mostrarVeterinario(@PathVariable("id") long idVeterinario) {
        Veterinario veterinario = service.searchById(idVeterinario);
        if (veterinario != null) {
            return veterinario;
        } else {
            throw new IllegalArgumentException("Veterinario no encontrado");
        }
    }

    // Agregar un veterinario
    @PostMapping("/add")
    public Veterinario agregarVeterinario(@RequestBody Veterinario veterinario) {
        if (veterinario.getCedula() == null || veterinario.getCedula().isEmpty()) {
            throw new IllegalArgumentException("Cédula no válida");
        }

        return veterinarioRepository.save(veterinario);
    }

    // Actualizar un veterinario
    @PutMapping("/update/{id}")
    public Veterinario updateVeterinario(@PathVariable("id") Long idVeterinario, @RequestBody Veterinario veterinario) {
        Veterinario veterinarioExistente = service.searchById(idVeterinario);

        if (veterinarioExistente != null) {
            veterinario.setId(idVeterinario);
            return service.update(veterinario);
        } else {
            throw new IllegalArgumentException("Veterinario no encontrado");
        }
    }

    // Eliminar un veterinario
    @DeleteMapping("/delete/{id}")
    public void eliminarVeterinario(@PathVariable("id") Long idVeterinario) {
        Veterinario veterinario = service.searchById(idVeterinario);

        if (veterinario != null) {
            service.deleteById(idVeterinario);
        } else {
            throw new IllegalArgumentException("Veterinario no encontrado");
        }
    }
    @GetMapping("/login-veterinario")
    public Veterinario autenticarUsuario(@RequestParam("cedula") String cedula) {
        boolean autenticado = service.verificarCredenciales(cedula);

        if (autenticado) {
            return service.searchByCedula(cedula);  // Retorna el usuario si lo encuentra, o null si no
        }
        return null;  // Retorna null si no se encuentra el usuario o no está autenticado
    }

    @GetMapping("/buscar")
    public List<Veterinario> buscarVeterinarios(@RequestParam("nombre") String nombre) {
        return service.buscarPorNombre(nombre);
    }
}
