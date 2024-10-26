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

import com.example.demo.servicio.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    
    @Autowired
    UsuarioService service;

    
    

    @GetMapping("/registro")
    public ResponseEntity<String> crearUsuario() {
        return new ResponseEntity<>("crear_usuario", HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<String> mostrarLoginForm(Model model) {
        model.addAttribute("usuario", new Usuario(0, "", "", 0, 0, null));
        return new ResponseEntity<>("login_usuario", HttpStatus.OK);
    }

    @GetMapping("/login-usuario")
    public ResponseEntity<Usuario> autenticarUsuario(@RequestParam("cedula") int cedula) {
        boolean autenticado = service.verificarCredenciales(cedula);

        if (autenticado) {
            Usuario usuario = service.searchByCedula(cedula);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> autenticarUsuario(@RequestParam("cedula") int cedula, Model model) {
        boolean autenticado = service.verificarCredenciales(cedula);
        
        if (autenticado) {
            Usuario usuario = service.searchByCedula(cedula);
            if (usuario != null) {
                model.addAttribute("usuario", usuario);
                model.addAttribute("mascotas", usuario.getMascotas());
                return new ResponseEntity<>("datalles_usuario", HttpStatus.OK); 
            }
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
        }
        
        return new ResponseEntity<>("login_usuario", HttpStatus.UNAUTHORIZED); 
    }

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> mostrarUsuarios() {
        List<Usuario> usuarios = service.searchAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/add")
    public ResponseEntity<String> mostrarFormularioCrear(Model model) {
        Usuario usuario = new Usuario(0, "", "", 0, 0, null);
        model.addAttribute("usuario", usuario);
        return new ResponseEntity<>("crear_usuario", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Usuario> agregarUsuario(@RequestBody Usuario usuario) {
        if (usuario.getCedula() <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Usuario saved = service.add(usuario);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
        usuario.setId(id); // Asegurar que el ID del usuario se establezca correctamente
        service.update(usuario); // Usar el servicio para actualizar
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/update/{id}")
    public ResponseEntity<String> mostrarFormularioEditar(Model model, @PathVariable("id") int idusuario) {
        Usuario usuario = service.searchById(idusuario);
        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("usuario", usuario);
        return new ResponseEntity<>("modificar_usuario", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable("id") Integer idusuario) {
        Usuario usuario = service.searchById(idusuario);
                //.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        service.deleteById(idusuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Usuario> mostrarUsuario(@PathVariable("id") int idUsuario) {
        Usuario usuario = service.searchById(idUsuario);
        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/{id}/mascotas")
    public ResponseEntity<List<Mascota>> obtenerMascotasPorUsuario(@PathVariable("id") int idUsuario) {
        List<Mascota> mascotas = service.findMascotasByUsuarioId(idUsuario);
        return new ResponseEntity<>(mascotas, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Usuario>> buscarUsuarios(@RequestParam("nombre") String nombre) {
        List<Usuario> usuarios = service.buscarPorNombre(nombre);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}
