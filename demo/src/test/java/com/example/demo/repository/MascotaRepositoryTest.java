package com.example.demo.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entidades.Mascota;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.UsuarioRepository;
import com.example.demo.repositorio.VeterinarioRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class MascotaRepositoryTest {
    
    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Test
    public void MascotaRepository_save_Mascota() {

        //Arrange
        Mascota mascota = new Mascota("Firulais", "Pastor Aleman", 5, 20, "foto", "ninguna", Mascota.Estado.Activo);

        //Act
        Mascota savedMascota=mascotaRepository.save(mascota);

        //Arrange
        Assertions.assertThat(savedMascota).isNotNull();
    }

    
}
