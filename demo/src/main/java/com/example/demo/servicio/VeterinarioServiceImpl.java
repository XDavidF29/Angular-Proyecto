package com.example.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Tratamiento;
import com.example.demo.entidades.Usuario;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.VeterinarioRepository;
import com.example.demo.repositorio.TratamientoRepository;

@Service    
public class VeterinarioServiceImpl implements VeterinarioService {
    
    @Autowired
    private VeterinarioRepository repo;

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Override
    public Veterinario searchById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));
    }

    @Override
    public List<Veterinario> searchAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Veterinario update(Veterinario veterinario) {
        
        return repo.save(veterinario); // Guardar y devolver el veterinario actualizado
    }

    @Override
    public List <Tratamiento> findTratamientosByVeterinarioId(Integer veterinarioId){
        return tratamientoRepository.findByVeterinarioId(veterinarioId);
    }

    @Override
    public void add(Veterinario veterinario) {
        repo.save(veterinario);
    }

    @Override
    public boolean verificarCredenciales(String cedula, String contrasena) {
        Veterinario veterinario = repo.findByCedula(cedula);
    
        // Debugging: Log del veterinario encontrado
        if (veterinario == null) {
            System.out.println("No se encontró un veterinario con la cédula: " + cedula);
            return false;
        } else {
            System.out.println("Veterinario encontrado: " + veterinario.getCedula());
        }
    
        // Verifica si la contraseña coincide
        if (!veterinario.getPassword().equals(contrasena)) {
            System.out.println("Contraseña incorrecta para el veterinario con cédula: " + cedula);
            return false;
        }
    
        // Si ambas verificaciones son correctas
        return true;
    }
    


    @Override
    public Veterinario searchByCedula(String cedula) {
        return repo.findByCedula(cedula);
    }

    @Override
    public List<Veterinario> buscarPorNombre(String nombre) {
        return repo.buscarPorNombre(nombre);
    }
}
