package com.example.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Mascota;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.UsuarioRepository;

@Service    
public class MascotaServiceImpl implements MascotaService{
    @Autowired
    MascotaRepository repo;

    @Autowired
    UsuarioRepository repositorioUsuario;

    @Autowired
    TratamientoRepository repoTratamiento;


    @Override
    public Mascota searchById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public List<Mascota> searchAll() {
        return repo.findAll();
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

    /*@Override
    public List<Mascota> searchMascotasByUsuario(Integer usuarioId) {
        return repo.findByUsuarioId(usuarioId);
    }*/
}
