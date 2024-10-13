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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Usuario;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.UsuarioRepository;
import com.example.demo.servicio.MascotaService;
import com.example.demo.servicio.UsuarioService;

//@SuppressWarnings("unused")
@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    @Autowired
    UsuarioService service;

    @Autowired
    MascotaService mascotaService;

    @Autowired
    UsuarioRepository UsuarioRepository;

    @Autowired
    MascotaRepository mascotaRepository;

    @GetMapping("/registro")
    public String crear_Usuario(){
        return "crear_usuario";
    }

    @GetMapping("/login")
    public String mostrarLoginForm(Model model) {
        model.addAttribute("usuario", new Usuario(0, "", "", 0, 0,  null));
        return "login_usuario";
    }

    
    @PostMapping("/login")
    public String autenticarUsuario(@RequestParam("cedula") int cedula, Model model) {

        boolean autenticado = service.verificarCredenciales(cedula);
        
        if (autenticado) {

            Usuario usuario = service.searchByCedula(cedula);

            if (usuario != null) {
                
                model.addAttribute("usuario", usuario);
                model.addAttribute("mascotas", usuario.getMascotas());
                return "datalles_usuario"; 
            }
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
        }
        
        return "login_usuario"; 
    }

    @GetMapping("/all")
    public List<Usuario>mostrarMascotas(Model model) {
        //model.addAttribute("usuarios", service.searchAll());
        return service.searchAll();
    }

    @GetMapping("/add")
    public String mostrarFormularioCrear(Model model) {
        Usuario usuario = new Usuario(0,"", "", 0, 0,null);
        model.addAttribute("usuario", usuario);
        return "crear_usuario";
    }

    @PostMapping("/add")
    public ResponseEntity<?> agregarUsuario(@RequestBody Usuario usuario) {
        try {
            // Validar que todos los campos necesarios estén presentes
            if (usuario.getCedula() <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cédula no válida");
            }

            // Verificar si la cédula ya existe
            //if (service.findByCedula(usuario.getCedula()) != null) {
            //    return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un usuario con esa cédula");
            //}

            // Guardar el nuevo usuario
            Usuario usuarioGuardado = UsuarioRepository.save(usuario);
            
            return ResponseEntity.ok(usuarioGuardado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Error al registrar el usuario: " + e.getMessage());
        }
    }


    @PutMapping("/update/{id}")
    public void updateUsuario(@RequestBody Usuario usuario){
        //usuario.setId(idusuario);
        service.update(usuario);
    }

    @GetMapping("/update/{id}")
    public String mostrarFormularioEditar(Model model, @PathVariable("id") int idusuario) {
        Usuario usuario = service.searchById(idusuario);
        if (usuario == null) {
            throw new NotFoundException(idusuario);
        }
        model.addAttribute("usuario", usuario);
        return "modificar_usuario";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable("id") Integer idusuario) {
        // Buscar usuario por ID
        Usuario usuario = UsuarioRepository.findById(idusuario).orElse(null);
    
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    
        // Si hay mascotas asociadas, manejar la lógica deseada
        if (usuario.getMascotas() != null && !usuario.getMascotas().isEmpty()) {
            // Aquí puedes decidir si eliminar las mascotas o dejar que se mantengan
            // Por ejemplo, puedes eliminar las mascotas asociadas:
            for (Mascota mascota : usuario.getMascotas()) {
                mascota.setUsuario(null); // Desasociar la mascota del usuario
                mascotaRepository.save(mascota); // Guardar la mascota desasociada
            }
        }
    
        // Eliminar el usuario
        UsuarioRepository.delete(usuario);
        return ResponseEntity.ok("Usuario eliminado con éxito");
    }
    

    @GetMapping("/find/{id}")
    public ResponseEntity<Usuario> mostrarUsuario(@PathVariable("id") int idUsuario) {
        Usuario usuario = service.searchById(idUsuario);
    
        if (usuario != null) {
            // Las mascotas ya deberían estar cargadas por la relación en el Usuario
            return ResponseEntity.ok(usuario); // Devuelve el usuario junto con sus mascotas
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Manejar el caso donde no se encuentra el usuario
        }
    }
    
    @GetMapping("/{id}/mascotas") // Suponiendo que estás utilizando 'cedula' para la búsqueda
    public List<Mascota> obtenerMascotasPorUsuario(@PathVariable("id") int idUsuario) {
        return service.findMascotasByUsuarioId(idUsuario);
    }
} 