package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.example.demo.DTOs.UsuarioDTO;
import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.UserEntity;
import com.example.demo.entidades.Usuario;
import com.example.demo.repositorio.UserRepository;
import com.example.demo.security.CustomUserDetailService;
import com.example.demo.DTOs.UsuarioMapper;
import com.example.demo.security.JWTGenerator;

import com.example.demo.servicio.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    
    @Autowired
    UsuarioService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailService customUserDetailService;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JWTGenerator jwtGenerator;



    @GetMapping("/registro")
    public ResponseEntity<String> crearUsuario() {
        return new ResponseEntity<>("crear_usuario", HttpStatus.OK);
    }

    // @GetMapping("/login")
    // public ResponseEntity<String> mostrarLoginForm(Model model) {
    //     model.addAttribute("usuario", new Usuario(0, "", "", 0, 0, null));
    //     return new ResponseEntity<>("login_usuario", HttpStatus.OK);
    // }


    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody Usuario usuario) {
        // int cedula = usuario.getCedula();
        // boolean autenticado = service.verificarCredenciales(cedula);

        // if (autenticado) {
        //     Usuario usuarioActual = service.searchByCedula(cedula);
        //     if (usuario != null) {
        //         UsuarioDTO usuarioDTO= UsuarioMapper.INSTANCE.convert(usuarioActual);
        //         return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK); 
        //     }
        // }

        // return new ResponseEntity<>("Correo o contraseña incorrectos", HttpStatus.UNAUTHORIZED); 

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(usuario.getCedula(), "123"));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<String>(token, HttpStatus.OK);
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
    public ResponseEntity agregarUsuario(@RequestBody Usuario usuario) {
        // Mensaje para verificar los datos recibidos
        System.out.println("Datos recibidos: " + usuario);

        if (usuario.getCedula() <= 0) {
            System.out.println("Error: Cédula no válida.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Verificar si el usuario ya existe por cédula
        if(userRepository.existsByUsername(String.valueOf(usuario.getCedula()))) {
            return new ResponseEntity<String>("Este usuario ya existe",HttpStatus.CONFLICT);
        }

        UserEntity userEntity = customUserDetailService.UserToUser(usuario);
        usuario.setUser(userEntity);
        Usuario user = service.add(usuario);
        UsuarioDTO newUsuario = UsuarioMapper.INSTANCE.convert(user);

        if(newUsuario == null) {
            return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UsuarioDTO>(newUsuario, HttpStatus.CREATED);
    }


    @GetMapping("/details")
    public ResponseEntity<Usuario> buscarUsuario() {
        Usuario usuario = service.searchByCedula(Integer.parseInt(
                SecurityContextHolder.getContext().getAuthentication().getName()));

        if (usuario == null) {
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
        usuario.setId(id); // Asegurar que el ID del usuario se establezca correctamente
        service.update(usuario); // Usar el servicio para actualizar
        UsuarioDTO newUsuario = UsuarioMapper.INSTANCE.convert(usuario);
        return new ResponseEntity<UsuarioDTO>(newUsuario, HttpStatus.OK);
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
