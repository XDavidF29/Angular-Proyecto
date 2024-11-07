package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.VeterinarioDTO;
import com.example.demo.DTOs.VeterinarioMapper;
import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Tratamiento;
import com.example.demo.entidades.UserEntity;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.VeterinarioRepository;
import com.example.demo.servicio.VeterinarioService;
import com.example.demo.repositorio.UserRepository;
import com.example.demo.security.CustomUserDetailService;
import com.example.demo.security.JWTGenerator;

@RestController
@RequestMapping("/veterinario")
@CrossOrigin(origins = "*")
public class VeterinarioController {

    @Autowired
    VeterinarioService service;

    @Autowired
    VeterinarioRepository veterinarioRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JWTGenerator jwtGenerator;

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

    // Obtener tratamientos por veterinario
    @GetMapping("/{id}/tratamientos")
    public List<Tratamiento> obtenerTratamientosPorVeterinario(@PathVariable("id") int idVeterinario) {
        return service.findTratamientosByVeterinarioId(idVeterinario);
    }

    // Agregar un veterinario
    @PostMapping("/add")
    public ResponseEntity agregarVeterinario(@RequestBody Veterinario veterinario) {
        System.out.println("Iniciando el método agregarVeterinario...");

        if (veterinario.getCedula() == null || veterinario.getCedula().isEmpty()) {
            System.out.println("Cédula no válida: " + veterinario.getCedula());
            throw new IllegalArgumentException("Cédula no válida");
        }

        if(userRepository.existsByUsername(String.valueOf(veterinario.getCedula()))) {
            return new ResponseEntity<String>("Este veterinario ya existe", HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = customUserDetailService.VeterinarioToUser(veterinario);
        veterinario.setUser(userEntity);
        Veterinario veterinarioDB = service.add(veterinario);
        VeterinarioDTO newVeterinario = VeterinarioMapper.INSTANCE.convert(veterinarioDB);

        if(newVeterinario == null) {
            return new ResponseEntity<VeterinarioDTO>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<VeterinarioDTO>(newVeterinario, HttpStatus.CREATED);
    }


    // Actualizar un veterinario

    @PutMapping("/update/{id}")
    public void updateVeterinario(@RequestBody Veterinario veterinario) {
        
        service.update(veterinario);
    }

    @GetMapping("/update/{id}")
    public Veterinario updateVeterinarioFormulario(@PathVariable("id") Long idVeterinario, @RequestBody Veterinario veterinario) {
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
    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody Veterinario veterinarioLogin) {
        // String cedula = veterinarioLogin.getCedula();
        // String contrasena = veterinarioLogin.getPassword();

        // Veterinario veterinario = service.searchByCedula(cedula);

        // // Verificar si el veterinario existe y si las credenciales son correctas
        // if (veterinario == null) {
        //     return new ResponseEntity<>("Veterinario no encontrado", HttpStatus.NOT_FOUND);
        // } else if (!service.verificarCredenciales(cedula, contrasena)) {
        //     return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        // }

        // // Convertir a DTO si se autenticó correctamente
        // VeterinarioDTO veterinarioDTO = VeterinarioMapper.INSTANCE.convert(veterinario);
        // return new ResponseEntity<VeterinarioDTO>(veterinarioDTO, HttpStatus.OK);

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(veterinarioLogin.getCedula(), veterinarioLogin.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<String>(token, HttpStatus.OK);
    }


    @GetMapping("/details")
    public ResponseEntity<VeterinarioDTO> buscarEstudiante() {

        String cedula = SecurityContextHolder.getContext().getAuthentication().getName();
        Veterinario veterinario = service.searchByCedula(cedula);

        VeterinarioDTO veterinarioDTO = VeterinarioMapper.INSTANCE.convert(veterinario);

        if (veterinario == null) {
            return new ResponseEntity<VeterinarioDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<VeterinarioDTO>(veterinarioDTO, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public List<Veterinario> buscarVeterinarios(@RequestParam("nombre") String nombre) {
        return service.buscarPorNombre(nombre);
    }
}
