package com.example.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Usuario;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository repo;

    @Autowired
    TratamientoRepository  repoTratamiento;

    @Autowired
    MascotaRepository repoMascota;

    @Override
    public Usuario searchById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public void update(Usuario usuario) {
        // Buscar el usuario actual en la base de datos
        Usuario usuarioExistente = repo.findById(usuario.getId()).orElse(null);

        if (usuarioExistente != null) {
            // Actualizar los detalles del usuario
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setCorreo(usuario.getCorreo());
            usuarioExistente.setCelular(usuario.getCelular());
            usuarioExistente.setCedula(usuario.getCedula());

            // Actualizar las mascotas
            for (Mascota mascotaActualizada : usuario.getMascotas()) {
                // Verificar si la mascota ya existe o es nueva
                if (mascotaActualizada.getId() == null) {
                    // Si no tiene ID, es una nueva mascota, se asigna el usuario existente
                    mascotaActualizada.setUsuario(usuarioExistente);
                    usuarioExistente.getMascotas().add(mascotaActualizada);
                } else {
                    // Si tiene ID, actualizar los detalles de la mascota existente
                    for (Mascota mascotaExistente : usuarioExistente.getMascotas()) {
                        if (mascotaExistente.getId().equals(mascotaActualizada.getId())) {
                            mascotaExistente.setNombre(mascotaActualizada.getNombre());
                            mascotaExistente.setRaza(mascotaActualizada.getRaza());
                            mascotaExistente.setEdad(mascotaActualizada.getEdad());
                            mascotaExistente.setPeso(mascotaActualizada.getPeso());
                            mascotaExistente.setEnfermedad(mascotaActualizada.getEnfermedad());
                            mascotaExistente.setEstado(mascotaActualizada.getEstado());
                            break;
                        }
                    }
                }
            }

            // Guardar el usuario con las mascotas actualizadas
            repo.save(usuarioExistente);
        }
    }

    @Override
    public void add(Usuario usuario) {
        repo.save(usuario);
    }

    @Override
    public List<Usuario> searchAll() {
        return repo.findAll();
    }

    @Override
    public void addMascotaToUsuario(int idUsuario, Mascota mascota) {
        Usuario usuario = repo.findById(idUsuario).orElse(null);
        if (usuario != null) {
            usuario.getMascotas().add(mascota);
            repo.save(usuario);
        }
    }

    @Override
    public boolean verificarCredenciales(int cedula) {
        Usuario usuario = repo.findByCedula(cedula);
        return usuario != null && usuario.getCedula()==cedula;
    }

    @Override
    public Usuario searchByCedula(int cedula) {
        return repo.findByCedula(cedula);
    }

    @Override
    public List<Mascota> findMascotasByUsuarioId(Integer usuarioId) {
        return repoMascota.findByUsuarioCedula(usuarioId);
    }

    @Override
    public List<Usuario> buscarPorNombre(String nombre) {
        return repo.buscarPorNombre(nombre);
    }
}    