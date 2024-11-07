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

    public Usuario update(Usuario usuario) {
        // Buscar el usuario actual en la base de datos
        Usuario usuarioExistente = repo.findById(usuario.getId()).orElse(null);
    
        if (usuarioExistente != null) {
            // Actualizar los detalles del usuario
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setCorreo(usuario.getCorreo());
            usuarioExistente.setCelular(usuario.getCelular());
            usuarioExistente.setCedula(usuario.getCedula());
    
            // Guardar el usuario actualizado (sin tocar las mascotas)
            repo.save(usuarioExistente);
        }
        return usuarioExistente;
    }
    

    @Override
    public Usuario add(Usuario usuario) {
        return repo.save(usuario);
        
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

    @Override
    public Usuario findByCorreo(String correo) {
        return repo.findByCorreo(correo);
    }
}    