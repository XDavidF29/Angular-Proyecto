package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidades.Mascota;

@SuppressWarnings("unused")
public interface MascotaService {
    public Mascota searchById(Long id);
    public List<Mascota> searchAll();
    public void deleteById(Long id);
    public void update(Mascota mascota);
    public void add(Mascota mascota);
    //public List<Mascota> searchMascotasByUsuario(Integer usuarioId);
}
