package com.example.demo.controller;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.controlador.UsuarioController;
import com.example.demo.entidades.Usuario;
import com.example.demo.servicio.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@WebMvcTest(controllers = UsuarioController.class)
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class UsuarioControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void UsuarioControllerTest_agregarUsuario_Usuario() throws Exception {
        Usuario usuario = new Usuario(
            "Juan",
            "juan.hola",
            123,
            123,
            null
        );

        when(usuarioService.add(Mockito.any(Usuario.class))).thenReturn(usuario);

        ResultActions response = mockMvc.perform(
            post("/usuario/add")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(usuario)));

        response.andExpect(status().isCreated())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.nombre").value("Juan"))
        .andExpect(jsonPath("$.correo").value(usuario.getCorreo()));


    }
    @Test
    public void UsuarioController_mostrarUsuarios_UsuariosList() throws Exception {
        // Arrange
        when(usuarioService.searchAll()).thenReturn(
            List.of(
                new Usuario(
                    "juan",
                    "sjbdvhsbdv",
                    123,
                    123,
                    null
                ),
                new Usuario(
                    "juan",
                    "ahkbdahb",
                    456,
                    456,
                    null
                )
            )
        );

        // Act
        ResultActions response = mockMvc.perform(get("/usuario/all"));

        // Assert
        response.andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.size()").value(2));
    }

}