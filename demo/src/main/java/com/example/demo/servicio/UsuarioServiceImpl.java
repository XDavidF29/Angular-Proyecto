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

    @Override
    public void update(Usuario usuario) {
        repo.save(usuario);
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
}    