package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Usuario;

public interface UsuarioService {
    public Usuario searchById(int id);
    public List<Usuario> searchAll();
    public void deleteById(Integer id);
    public Usuario update(Usuario usuario);
    public Usuario add(Usuario usuario);
    public Usuario searchByCedula(int cedula);
    public void addMascotaToUsuario(int idUsuario, Mascota mascota);  
    public boolean verificarCredenciales(int cedula);
    public List<Mascota> findMascotasByUsuarioId(Integer usuarioId);
    public List<Usuario> buscarPorNombre(String nombre);
} 
