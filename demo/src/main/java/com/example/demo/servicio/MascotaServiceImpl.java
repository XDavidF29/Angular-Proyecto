package com.example.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Medicamento;
import com.example.demo.entidades.Tratamiento;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.MedicamentoRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.UsuarioRepository;
import com.example.demo.repositorio.VeterinarioRepository;

@Service    
public class MascotaServiceImpl implements MascotaService{
    @Autowired
    MascotaRepository repo;

    @Autowired
    VeterinarioRepository veterinarioRepo;
    
    @Autowired
    UsuarioRepository repositorioUsuario;

    @Autowired
    TratamientoRepository repoTratamiento;

    @Autowired
    MedicamentoRepository medicamentoRepository;


    @Override
    public Mascota searchById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public List<Mascota> searchAll() {
        return repo.findAll();
    }

    @Override
    public List<Mascota> buscarPorNombre(String nombre) {
        return repo.buscarPorNombre(nombre);
    }

    @Override
    public void deleteById(Long id) {
            // Primero eliminar los tratamientos relacionados con la mascota
        repoTratamiento.deleteByMascotaId(id);
    
            // Luego eliminar la mascota
        repo.deleteById(id);
    }

    @Override
    public void update(Mascota mascota) {
        // TODO Auto-generated method stub arreglar la asociacion 
        //mascota.setUsuario(UsuarioRepository.);
        repo.save(mascota);
    }

    @Override
    public void add(Mascota mascota) {
        // TODO Auto-generated method stub
        repo.save(mascota);
    }

    @Override
    public Mascota agregarTratamiento(Long mascotaId, Tratamiento tratamiento, String cedulaVeterinario) {
        // Buscar la mascota por su ID
        Mascota mascota = repo.findById(mascotaId).orElse(null);

        // Buscar el veterinario por su cédula
        Veterinario veterinario = veterinarioRepo.findByCedula(cedulaVeterinario);

        if (mascota != null && veterinario != null) {
            // Asocia el tratamiento con la mascota y el veterinario
            tratamiento.setMascota(mascota);
            tratamiento.setVeterinario(veterinario);

            // Si no hay medicamentos seleccionados, se podría asignar un medicamento base
            if (tratamiento.getMedicamentos() == null || tratamiento.getMedicamentos().isEmpty()) {
                List<Medicamento> medicamentosBase = medicamentoRepository.findAll();  // Lógica para obtener los medicamentos base
                tratamiento.setMedicamentos(medicamentosBase);
            }

            // Guardar el tratamiento en el repositorio
            repoTratamiento.save(tratamiento);

            // Añadir el tratamiento a la lista de la mascota
            mascota.getTratamientos().add(tratamiento);

            // Guardar la mascota con el tratamiento asignado
            return repo.save(mascota);
        }

        return null;  // Devolver null si no se encuentra la mascota o el veterinario
    }

}
