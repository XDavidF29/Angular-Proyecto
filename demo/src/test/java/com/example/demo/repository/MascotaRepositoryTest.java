package com.example.demo.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.DemoApplication;
import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Usuario;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.UsuarioRepository;
import com.example.demo.repositorio.VeterinarioRepository;

import junit.framework.Assert;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DemoApplication.class) 
public class MascotaRepositoryTest {
    
    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @BeforeEach
    public void setUp() {
        mascotaRepository.save(new Mascota("Firulais", "Pastor Aleman", 5, 20, "foto", "ninguna", Mascota.Estado.Activo));
        mascotaRepository.save(new Mascota("Pepe", "Chihuahua", 3, 10, "foto", "ninguna", Mascota.Estado.Activo));
        mascotaRepository.save(new Mascota("Luna", "Pitbull", 4, 15, "foto", "ninguna", Mascota.Estado.Activo));
    
        usuarioRepository.save(new Usuario("Juan", "juan@example.com", 1234567, 1234567, null));
        usuarioRepository.save(new Usuario("Pedro", "pedro@example.com", 1234567, 1234567, null));

        //Asociar mascotas a usuarios
        Usuario usuario1 = usuarioRepository.findById(1).get();
        Usuario usuario2 = usuarioRepository.findById(2).get();
        Mascota mascota1 = mascotaRepository.findById(1L).get();
        Mascota mascota2 = mascotaRepository.findById(2L).get();
        Mascota mascota3 = mascotaRepository.findById(3L).get();

        mascota1.setUsuario(usuario1);
        mascota2.setUsuario(usuario1);
        mascota3.setUsuario(usuario2);

    }

    @Test
    public void MascotaRepository_save_Mascota() {

        //Arrange
        Mascota mascota = new Mascota("Firulais", "Pastor Aleman", 5, 20, "foto", "ninguna", Mascota.Estado.Activo);

        //Act
        Mascota savedMascota=mascotaRepository.save(mascota);

        //Arrange
        Assertions.assertThat(savedMascota).isNotNull();
    }

    @Test
    public void MascotaRepository_findAll_NotEmptyList() {

        //Arrange
        Mascota mascota = new Mascota("Firulais", "Pastor Aleman", 5, 20, "foto", "ninguna", Mascota.Estado.Activo);
        
        //Act
        mascotaRepository.save(mascota);
        List<Mascota> mascotas = mascotaRepository.findAll();

        //Assert
        Assertions.assertThat(mascotas).isNotNull();
        Assertions.assertThat(mascotas.size()).isEqualTo(1);
        Assertions.assertThat(mascotas.size()).isGreaterThan(0);
    }
    
}
