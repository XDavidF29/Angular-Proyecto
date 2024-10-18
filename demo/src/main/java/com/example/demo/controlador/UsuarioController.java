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
    public String crearUsuario() {
        return "crear_usuario";
    }

    @GetMapping("/login")
    public String mostrarLoginForm(Model model) {
        model.addAttribute("usuario", new Usuario(0, "", "", 0, 0, null));
        return "login_usuario";
    }

    @GetMapping("/login-usuario")
    public Usuario autenticarUsuario(@RequestParam("cedula") int cedula) {
        boolean autenticado = service.verificarCredenciales(cedula);

        if (autenticado) {
            return service.searchByCedula(cedula);  // Retorna el usuario si lo encuentra, o null si no
        }
        return null;  // Retorna null si no se encuentra el usuario o no está autenticado
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
    public List<Usuario> mostrarUsuarios() {
        return service.searchAll();
    }

    @GetMapping("/add")
    public String mostrarFormularioCrear(Model model) {
        Usuario usuario = new Usuario(0, "", "", 0, 0, null);
        model.addAttribute("usuario", usuario);
        return "crear_usuario";
    }

    @PostMapping("/add")
    public void agregarUsuario(@RequestBody Usuario usuario) {
        // Validar que todos los campos necesarios estén presentes
        if (usuario.getCedula() <= 0) {
            throw new IllegalArgumentException("Cédula no válida");
        }
        // Guardar el nuevo usuario
        UsuarioRepository.save(usuario);
    }

    @PutMapping("/update/{id}")
    public void updateUsuario(@RequestBody Usuario usuario) {
        
        service.update(usuario);
    }

    @GetMapping("/update/{id}")
    public String mostrarFormularioEditar(Model model, @PathVariable("id") int idusuario) {
        Usuario usuario = service.searchById(idusuario);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        model.addAttribute("usuario", usuario);
        return "modificar_usuario";
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarUsuario(@PathVariable("id") Integer idusuario) {
        Usuario usuario = UsuarioRepository.findById(idusuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        UsuarioRepository.delete(usuario);
    }

    @GetMapping("/find/{id}")
    public Usuario mostrarUsuario(@PathVariable("id") int idUsuario) {
        Usuario usuario = service.searchById(idUsuario);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        return usuario; // Devuelve el usuario junto con sus mascotas
    }

    @GetMapping("/{id}/mascotas")
    public List<Mascota> obtenerMascotasPorUsuario(@PathVariable("id") int idUsuario) {
        return service.findMascotasByUsuarioId(idUsuario);
    }

    @GetMapping("/buscar")
    public List<Usuario> buscarUsuarios(@RequestParam("nombre") String nombre) {
        return service.buscarPorNombre(nombre);
    }
}
