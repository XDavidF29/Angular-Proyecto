package com.example.demo.controlador;

import java.util.ArrayList;
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
import com.example.demo.entidades.Medicamento;
import com.example.demo.entidades.Tratamiento;
import com.example.demo.entidades.Usuario;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.UsuarioRepository;
import com.example.demo.servicio.MascotaService;
import com.example.demo.servicio.UsuarioService;
import com.example.demo.servicio.MedicamentoService;


@RestController
@RequestMapping("/mascota")
@CrossOrigin(origins = "http://localhost:4200")  // Para permitir peticiones del frontend Angular
public class MascotaController {

    @Autowired
    MascotaService service;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    MedicamentoService medicamentoService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired
    TratamientoRepository  tratamientoRepository;


    // Obtener todas las mascotas
    @GetMapping("/all")
    public List<Mascota> mostrarMascotas() {
        return service.searchAll();
    }

    @GetMapping("/find/{id}")
    public Mascota mostrarMascota(@PathVariable("id") Long idMascota) {
        return service.searchById(idMascota);
    }

    @GetMapping("/add")
    public String mostrarFormularioCrear(Model model) {
        Mascota mascota = new Mascota("", "", 0, 0, "", "", Mascota.Estado.Activo);
        System.out.println("Mostrar formulario para crear nueva mascota.");
        model.addAttribute("mascota", mascota);
        return "crear_mascota";
    }

    @PostMapping("/add")
public ResponseEntity<Mascota> crearMascota(@RequestBody Mascota mascota) {
    try {
        System.out.println("Intentando crear una nueva mascota.");

        // Validar que se haya enviado la cédula del usuario
        if (mascota.getUsuario() == null || mascota.getUsuario().getCedula() == 0) {
            System.out.println("Error: se necesita una cédula válida del usuario.");
            throw new IllegalArgumentException("Error: se necesita una cédula válida del usuario.");
        }

        // Buscar el usuario por la cédula
        System.out.println("Buscando usuario con cédula: " + mascota.getUsuario().getCedula());
        Usuario usuario = usuarioRepository.findByCedula(mascota.getUsuario().getCedula());

        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            throw new IllegalArgumentException("Error: Usuario no encontrado.");
        }

        // Asignar el usuario a la mascota
        System.out.println("Usuario encontrado, asignando usuario a la mascota.");
        mascota.setUsuario(usuario);

        // Inicializar la lista de tratamientos si es null
        if (mascota.getTratamientos() == null) {
            System.out.println("Inicializando lista de tratamientos vacía.");
            mascota.setTratamientos(new ArrayList<>());
        }

        // Guardar la nueva mascota
        System.out.println("Guardando la nueva mascota.");
        Mascota nuevaMascota = mascotaRepository.save(mascota);

        // Retornar la mascota guardada con su ID generado
        System.out.println("Mascota creada exitosamente.");
        return ResponseEntity.ok(nuevaMascota);

    } catch (Exception e) {
        // Captura cualquier error y devuelve una respuesta con un mensaje claro
        System.out.println("Error al registrar la mascota: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null); // En caso de error, devuelve null o un ResponseEntity vacío
    }
}



    

    @PutMapping("/update/{id}")
    public String updateMascota(@PathVariable("id") Long idMascota, @RequestBody Mascota mascota) {
        // Buscar si la mascota existe en la base de datos
        Mascota mascotaExistente = mascotaRepository.findById(idMascota).orElse(null);

        if (mascotaExistente != null) {
            // Actualizar solo los campos necesarios (evitar modificar el ID o el usuario)
            mascotaExistente.setNombre(mascota.getNombre());
            mascotaExistente.setRaza(mascota.getRaza());
            mascotaExistente.setEdad(mascota.getEdad());
            mascotaExistente.setPeso(mascota.getPeso());
            mascotaExistente.setFoto(mascota.getFoto());
            mascotaExistente.setEnfermedad(mascota.getEnfermedad());
            mascotaExistente.setEstado(mascota.getEstado());

            // Si el objeto recibido no tiene usuario, mantenemos el usuario existente
            if (mascota.getUsuario() == null) {
                mascotaExistente.setUsuario(mascotaExistente.getUsuario());
            } else {
                // Si se proporciona un nuevo usuario, asignarlo (si es necesario)
                mascotaExistente.setUsuario(mascota.getUsuario());
            }

            // Actualizar tratamientos relacionados
            if (mascota.getTratamientos() != null) {
                // Limpiar tratamientos existentes y añadir los nuevos
                mascotaExistente.getTratamientos().clear(); // Limpiar tratamientos existentes
                for (Tratamiento tratamiento : mascota.getTratamientos()) {
                    tratamiento.setMascota(mascotaExistente); // Asegura que cada tratamiento tenga la referencia correcta
                    mascotaExistente.getTratamientos().add(tratamiento); // Añadir nuevo tratamiento
                }
            }

            // Guardar la mascota actualizada
            mascotaRepository.save(mascotaExistente);

            // Retornar un mensaje de éxito
            return "{\"message\": \"Mascota actualizada exitosamente\"}";
        } else {
            // Si la mascota no existe, retornar un mensaje de error
            return "{\"message\": \"Mascota no encontrada\"}";
        }
    }


    
    @GetMapping("/update/{id}")
    public String mostrarFormularioEditar(Model model, @PathVariable("id") Long idMascota) {
        model.addAttribute("mascota", service.searchById(idMascota));
        return "modificar_mascota";
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarMascota(@PathVariable("id") Long idMascota) {
        try {
            service.deleteById(idMascota);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    @PostMapping("/tratamiento/{mascotaId}")
    public ResponseEntity<?> agregarTratamiento(@PathVariable Long mascotaId, @RequestBody Tratamiento tratamiento, @RequestParam String cedulaVeterinario) {
        List<String> medicamentosSinStock = new ArrayList<>(); // Lista para almacenar los medicamentos sin stock

        for (Medicamento medicamento : tratamiento.getMedicamentos()) {
            if (medicamento.getUnidadesDisponibles() <= 0) {
                medicamentosSinStock.add(medicamento.getNombre()); // O usar otro atributo que identifique el medicamento
            } else {
                medicamento.setUnidadesDisponibles(medicamento.getUnidadesDisponibles() - 1);
                medicamento.setUnidadesVendidas(medicamento.getUnidadesVendidas() + 1);
                medicamentoService.update(medicamento);
            }
        }

        // Si hay medicamentos sin stock, retorna un error con los nombres
        if (!medicamentosSinStock.isEmpty()) {
            return ResponseEntity.badRequest().body("No hay suficientes unidades disponibles para los siguientes medicamentos: " + String.join(", ", medicamentosSinStock));
        }

        // Solo si todo está bien, agrega el tratamiento a la mascota
        Mascota mascota = service.agregarTratamiento(mascotaId, tratamiento, cedulaVeterinario);

        if (mascota != null) {
            return ResponseEntity.ok(mascota); // Retorna la mascota actualizada si todo está bien
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna un error 404 si no se encuentra
        }
    }



    @GetMapping("/buscar")
    public List<Mascota> buscarMascotas(@RequestParam("nombre") String nombre) {
        return service.buscarPorNombre(nombre);
    }

    @GetMapping("/tratamientos/{mascotaId}")
    public List<Tratamiento> buscarTratamientosPorMascota(@PathVariable Long mascotaId) {
        return service.buscarTratamientosPorMascotaId(mascotaId);
    }

}
