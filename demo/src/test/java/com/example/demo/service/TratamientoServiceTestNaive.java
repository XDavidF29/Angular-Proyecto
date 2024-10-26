package com.example.demo.service;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.DemoApplication;
import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Tratamiento;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.servicio.TratamientoServiceImpl;

@SpringBootTest(classes = DemoApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class TratamientoServiceTestNaive {

    @Mock
    private TratamientoRepository tratamientoRepository;

    @InjectMocks
    private TratamientoServiceImpl tratamientoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchById() {
        
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setId(1);
        tratamiento.setFecha(new Date());
        tratamiento.setPrecio(200);

        when(tratamientoRepository.findById(1)).thenReturn(Optional.of(tratamiento));

        
        Tratamiento foundTratamiento = tratamientoService.searchById(1);

        
        Assertions.assertThat(foundTratamiento).isNotNull();
        Assertions.assertThat(foundTratamiento.getId()).isEqualTo(1);
        Assertions.assertThat(foundTratamiento.getPrecio()).isEqualTo(200);
    }

    @Test
    public void testSearchAll() {
        // Arrange
        Tratamiento tratamiento1 = new Tratamiento(new Date(), 200, new Mascota(), new Veterinario(), null);
        Tratamiento tratamiento2 = new Tratamiento(new Date(), 300, new Mascota(), new Veterinario(), null);
        
        when(tratamientoRepository.findAll()).thenReturn(Arrays.asList(tratamiento1, tratamiento2));

        // Act
        List<Tratamiento> tratamientos = tratamientoService.searchAll();

        // Assert
        Assertions.assertThat(tratamientos).isNotEmpty();
        Assertions.assertThat(tratamientos.size()).isEqualTo(2);
    }

    @Test
    public void testDeleteById() {
        // Act
        tratamientoService.deleteById(1);

        // Assert
        verify(tratamientoRepository, times(1)).deleteById(1);
    }

    @Test
    public void testUpdate() {
        // Arrange
        Tratamiento tratamiento = new Tratamiento(new Date(), 250, new Mascota(), new Veterinario(), null);

        // Act
        tratamientoService.update(tratamiento);

        // Assert
        verify(tratamientoRepository, times(1)).save(tratamiento);
    }

    @Test
    public void testAdd() {
        // Arrange
        Tratamiento tratamiento = new Tratamiento(new Date(), 500, new Mascota(), new Veterinario(), null);

        // Act
        tratamientoService.add(tratamiento);

        // Assert
        verify(tratamientoRepository, times(1)).save(tratamiento);
    }

   
}
