package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Usuario;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.UsuarioRepository;
import com.example.demo.servicio.MascotaService;
import com.example.demo.servicio.UsuarioService;

@RestController
@RequestMapping("/mascota")
@CrossOrigin(origins = "http://localhost:4200")  // Para permitir peticiones del frontend Angular
public class MascotaController {

    @Autowired
    MascotaService service;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    MascotaRepository mascotaRepository;

    // Obtener todas las mascotas
    @GetMapping("/all")
    public List<Mascota> mostrarMascotas() {
        return service.searchAll();
    }


    @GetMapping("/find/{id}")
    public Mascota mostrarMascota(@PathVariable("id") Long idMascota) {
        return service.searchById(idMascota);
    }

    
    /*@GetMapping("/info")    
    public String mostrarInformacionMascota(Model model){
        return service.searchById(idMascota);
    }*/
    @GetMapping("/add")
    public String mostrarFormularioCrear(Model model) {
        Mascota mascota = new Mascota("", "", 0, 0, "", "", "");
        model.addAttribute("mascota", mascota);
        return "crear_mascota";
    }

    @PostMapping("/add")
public ResponseEntity<?> agregarMascota(@RequestBody Mascota mascota) {
    try {
        // Verifica que el usuario exista a través de su cédula
        if (mascota.getUsuario() == null || mascota.getUsuario().getCedula() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cédula de usuario no proporcionada");
        }

        // Busca el usuario por su cédula
        Usuario usuario = usuarioRepository.findByCedula(mascota.getUsuario().getCedula());
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Usuario con cédula " + mascota.getUsuario().getCedula() + " no encontrado");
        }

        // Asocia el usuario a la mascota
        mascota.setUsuario(usuario);

        // Guarda la mascota
        Mascota mascotaGuardada = mascotaRepository.save(mascota);
        
        // Actualiza la relación del usuario con la nueva mascota
        usuario.getMascotas().add(mascotaGuardada);
        usuarioRepository.save(usuario);  // Guarda el usuario con la mascota actualizada

        return ResponseEntity.ok(mascotaGuardada);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Error al registrar la mascota: " + e.getMessage());
    }
}

    @GetMapping("/update/{id}")
    public String mostrarFormularioEditar(Model model, @PathVariable("id") Long idMascota) {
        model.addAttribute("mascota", service.searchById(idMascota));
        return "modificar_mascota";
    }

    @PutMapping("/update/{id}")
    public void updateMascota(@PathVariable("id") Long idMascota, @RequestBody Mascota mascota) {
        /*mascota.setId(idMascota);
        Mascota pet=service.searchById(idMascota);
        mascota.setUsuario(pet.getUsuario());
        mascota.setEstado(pet.getEstado());*/
        service.update(mascota);
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarMascota(@PathVariable("id") Long idMascota) {
        service.deleteById(idMascota);
    }    
    
}
