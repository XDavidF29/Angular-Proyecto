package com.example.demo.entidades;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.repositorio.MascotaRepository; // Import correcto
import com.example.demo.repositorio.MedicamentoRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.UsuarioRepository;
import com.example.demo.repositorio.VeterinarioRepository;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class DatabaseInit implements ApplicationRunner {
    
    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    VeterinarioRepository veterinarioRepository;

    @Autowired
    MedicamentoRepository medicamentoRepository;

    @Autowired
    TratamientoRepository tratamientoRepository;

    //@Autowired
    //TratamientoRepository tratamientoRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Guardar 50 usuarios
        usuarioRepository.save(new Usuario(null, "Juan", "juan@example.com", 123456789, 111222335, null));
        usuarioRepository.save(new Usuario(null, "Pedro", "pedro@example.com", 987654321, 444555666, null));
        usuarioRepository.save(new Usuario(null, "Carlos", "carlos@example.com", 112233445, 222333444, null));
        usuarioRepository.save(new Usuario(null, "Ana", "ana@example.com", 554433221, 665544332, null));
        usuarioRepository.save(new Usuario(null, "María", "maria@example.com", 777888999, 888777666, null));
        usuarioRepository.save(new Usuario(null, "Luis", "luis@example.com", 123123123, 321321321, null));
        usuarioRepository.save(new Usuario(null, "Sofía", "sofia@example.com", 456456456, 654654654, null));
        usuarioRepository.save(new Usuario(null, "Miguel", "miguel@example.com", 789789789, 987987987, null));
        usuarioRepository.save(new Usuario(null, "Raquel", "raquel@example.com", 147258369, 963852741, null));
        usuarioRepository.save(new Usuario(null, "David", "david@example.com", 258369147, 741852963, null));
        usuarioRepository.save(new Usuario(null, "Lucía", "lucia@example.com", 369147258, 852963741, null));
        usuarioRepository.save(new Usuario(null, "Ricardo", "ricardo@example.com", 987123654, 654321987, null));
        usuarioRepository.save(new Usuario(null, "Elena", "elena@example.com", 654987321, 321789654, null));
        usuarioRepository.save(new Usuario(null, "José", "jose@example.com", 123654789, 987456321, null));
        usuarioRepository.save(new Usuario(null, "Carmen", "carmen@example.com", 456789123, 789123456, null));
        usuarioRepository.save(new Usuario(null, "Francisco", "francisco@example.com", 741963852, 852147963, null));
        usuarioRepository.save(new Usuario(null, "Isabel", "isabel@example.com", 963741258, 258963147, null));
        usuarioRepository.save(new Usuario(null, "Jesús", "jesus@example.com", 159753486, 486357159, null));
        usuarioRepository.save(new Usuario(null, "Verónica", "veronica@example.com", 753159846, 846951753, null));
        usuarioRepository.save(new Usuario(null, "Gustavo", "gustavo@example.com", 987654321, 123456789, null));
        usuarioRepository.save(new Usuario(null, "Patricia", "patricia@example.com", 112233445, 554433221, null));
        usuarioRepository.save(new Usuario(null, "Felipe", "felipe@example.com", 998877665, 332211554, null));
        usuarioRepository.save(new Usuario(null, "Daniel", "daniel@example.com", 111222333, 444555666, null));
        usuarioRepository.save(new Usuario(null, "Andrea", "andrea@example.com", 666555444, 333222111, null));
        usuarioRepository.save(new Usuario(null, "Fernando", "fernando@example.com", 222111333, 666444555, null));
        usuarioRepository.save(new Usuario(null, "Rosa", "rosa@example.com", 555666777, 444333222, null));
        usuarioRepository.save(new Usuario(null, "Oscar", "oscar@example.com", 777666555, 222333444, null));
        usuarioRepository.save(new Usuario(null, "Natalia", "natalia@example.com", 888999000, 99888, null));
        usuarioRepository.save(new Usuario(null, "Sergio", "sergio@example.com", 333222111, 111222333, null));
        usuarioRepository.save(new Usuario(null, "Laura", "laura@example.com", 444555666, 666555444, null));
        usuarioRepository.save(new Usuario(null, "Hugo", "hugo@example.com", 222333444, 555666777, null));
        usuarioRepository.save(new Usuario(null, "Valeria", "valeria@example.com", 000111222, 333444555, null));
        usuarioRepository.save(new Usuario(null, "Alejandro", "alejandro@example.com", 555444333, 111000999, null));
        usuarioRepository.save(new Usuario(null, "Carla", "carla@example.com", 666777888, 222111000, null));
        usuarioRepository.save(new Usuario(null, "Ramiro", "ramiro@example.com", 111999888, 888777666, null));
        usuarioRepository.save(new Usuario(null, "Sara", "sara@example.com", 000555444, 333111222, null));
        usuarioRepository.save(new Usuario(null, "Iván", "ivan@example.com", 444333222, 666000999, null));
        usuarioRepository.save(new Usuario(null, "Paula", "paula@example.com", 333666555, 444222111, null));
        usuarioRepository.save(new Usuario(null, "Javier", "javier@example.com", 555999666, 777000111, null));
        usuarioRepository.save(new Usuario(null, "Ángela", "angela@example.com", 777333111, 888222000, null));
        usuarioRepository.save(new Usuario(null, "Mauricio", "mauricio@example.com", 999888777, 000111333, null));
        usuarioRepository.save(new Usuario(null, "Liliana", "liliana@example.com", 111222333, 444555666, null));
        usuarioRepository.save(new Usuario(null, "Rodrigo", "rodrigo@example.com", 222333444, 555666777, null));
        usuarioRepository.save(new Usuario(null, "Camila", "camila@example.com", 33344555, 666777888, null));
        usuarioRepository.save(new Usuario(null, "Matías", "matias@example.com", 444555666, 777888999, null));
        usuarioRepository.save(new Usuario(null, "Elisa", "elisa@example.com", 555666777, 888999000, null));
        usuarioRepository.save(new Usuario(null, "Pablo", "pablo@example.com", 666777888, 999000111, null));
        usuarioRepository.save(new Usuario(null, "Adriana", "adriana@example.com", 777888999, 1112223339, null));
        usuarioRepository.save(new Usuario(null, "Tomás", "tomas@example.com", 888999000, 222333444, null));
        usuarioRepository.save(new Usuario(null, "Olga", "olga@example.com", 999000111, 333444555, null));

        mascotaRepository.save(new Mascota("pepe", "chiquita", 1, 12.3f, "https://plus.unsplash.com/premium_photo-1676390051589-bead49b416a6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8ZG9nfGVufDB8fDB8fHww", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("lola", "chiquita", 1, 12.3f, "https://images.unsplash.com/photo-1534361960057-19889db9621e?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZG9nfGVufDB8fDB8fHww", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("max", "mediana", 2, 15.0f, "https://images.unsplash.com/photo-1503256207526-0d5d80fa2f47?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8ZG9nfGVufDB8fDB8fHww", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("bella", "mediana", 2, 14.7f, "https://images.unsplash.com/photo-1422565096762-bdb997a56a84?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8ZG9nfGVufDB8fDB8fHww", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("rocky", "grande", 3, 20.0f, "https://images.unsplash.com/photo-1504826260979-242151ee45b7?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8ZG9nfGVufDB8fDB8fHww", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("mila", "grande", 3, 19.5f, "https://images.unsplash.com/photo-1513904178077-6c5730ddd446?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTQ2fHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("duque", "grande", 3, 21.0f, "https://images.unsplash.com/photo-1517423440428-a5a00ad493e8?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8ZG9nfGVufDB8fDB8fHww", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("luna", "mediana", 2, 13.5f, "https://plus.unsplash.com/premium_photo-1666777247416-ee7a95235559?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8ZG9nfGVufDB8fDB8fHww", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("thor", "grande", 3, 22.0f, "https://plus.unsplash.com/premium_photo-1667673941713-ad4d4751c93b?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8ZG9nfGVufDB8fDB8fHww", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("zeus", "grande", 3, 23.0f, "https://images.unsplash.com/photo-1532592333381-a141e3f197c9?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTJ8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("fiona", "mediana", 2, 15.0f, "https://images.unsplash.com/photo-1530281700549-e82e7bf110d6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("boby", "chiquita", 1, 10.5f, "https://images.unsplash.com/photo-1518020382113-a7e8fc38eac9?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8ZG9nfGVufDB8fDB8fHww", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("daisy", "chiquita", 1, 11.0f, "https://images.unsplash.com/photo-1518717758536-85ae29035b6d?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTV8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("bruno", "mediana", 2, 16.0f, "https://plus.unsplash.com/premium_photo-1668208365386-4198381c6f6e?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTd8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("chico", "mediana", 2, 14.0f, "https://images.unsplash.com/photo-1505628346881-b72b27e84530?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTh8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("duque", "grande", 3, 20.5f, "https://images.unsplash.com/photo-1517849845537-4d257902454a?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTR8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("sasha", "grande", 3, 22.5f, "https://images.unsplash.com/photo-1477868433719-7c5f2731b310?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MzR8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("duque", "grande", 3, 21.5f, "https://plus.unsplash.com/premium_photo-1686090449483-89a9b794e7cf?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MzN8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("rex", "grande", 3, 23.5f, "https://plus.unsplash.com/premium_photo-1686090449483-89a9b794e7cf?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MzN8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("duque", "grande", 3, 22.0f, "https://plus.unsplash.com/premium_photo-1671496450438-6a9c90ab87a0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDF8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("leia", "chiquita", 1, 11.5f, "https://images.unsplash.com/photo-1477936432016-8172ed08637e?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDR8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("kenia", "mediana", 2, 14.0f, "https://images.unsplash.com/photo-1455287278107-115faab3eafa?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mzl8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("lili", "chiquita", 1, 12.0f, "https://images.unsplash.com/photo-1455103493930-a116f655b6c5?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDB8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("paco", "grande", 3, 21.5f, "https://images.unsplash.com/photo-1424709746721-b8fd0ff52499?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDJ8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("kira", "grande", 3, 24.0f, "https://images.unsplash.com/photo-1455380579765-810023662ea2?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDh8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("nina", "mediana", 2, 16.0f, "https://images.unsplash.com/photo-1559190394-df5a28aab5c5?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDN8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("oso", "grande", 3, 25.0f, "https://images.unsplash.com/photo-1483392707171-cb3e4b1cb8b0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NTB8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("simba", "grande", 3, 26.0f, "https://images.unsplash.com/photo-1497993950456-cdb57afd1cf1?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDZ8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("rocky", "grande", 3, 23.5f, "https://images.unsplash.com/photo-1541364983171-a8ba01e95cfc?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDd8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("balto", "grande", 3, 27.0f, "https://plus.unsplash.com/premium_photo-1677542200557-3c6856cc98b1?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NTN8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("rambo", "grande", 3, 26.5f, "https://plus.unsplash.com/premium_photo-1668211358705-b758b5d7f7cf?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDl8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("odin", "grande", 3, 28.0f, "https://images.unsplash.com/photo-1477884213360-7e9d7dcc1e48?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NTV8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("balto", "grande", 3, 24.5f, "https://images.unsplash.com/photo-1514984879728-be0aff75a6e8?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NTF8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("maya", "mediana", 2, 14.5f, "https://images.unsplash.com/photo-1556229174-5e42a09e45af?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NTJ8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("lucky", "chiquita", 1, 12.5f, "https://plus.unsplash.com/premium_photo-1664285640233-babe58af3c6b?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NTd8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("gala", "chiquita", 1, 13.0f, "https://images.unsplash.com/photo-1450096315186-13dc369ab43e?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NTh8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("toby", "mediana", 2, 17.0f, "https://images.unsplash.com/photo-1546447147-3fc2b8181a74?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NjJ8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("duque", "grande", 3, 29.0f, "https://images.unsplash.com/photo-1522276498395-f4f68f7f8454?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NTR8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("bola", "mediana", 2, 14.0f, "https://images.unsplash.com/photo-1522008693277-086ad6075b78?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NjB8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("oso", "grande", 3, 25.0f, "https://images.unsplash.com/photo-1544568100-847a948585b9?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NTl8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("tina", "chiquita", 1, 11.5f, "https://images.unsplash.com/photo-1502673530728-f79b4cab31b1?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Njh8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("tina", "chiquita", 1, 12.0f, "https://images.unsplash.com/photo-1498892156743-6d5da30bab8b?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NjR8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("tina", "chiquita", 1, 12.3f, "https://plus.unsplash.com/premium_photo-1669040674572-836c64a3ac70?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NjF8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("tina", "chiquita", 1, 11.9f, "https://plus.unsplash.com/premium_photo-1675881735301-5c9170e4793e?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NzN8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("tina", "chiquita", 1, 12.1f, "https://images.unsplash.com/photo-1543466835-00a7907e9de1?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OTV8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("tina", "chiquita", 1, 12.3f, "https://images.unsplash.com/photo-1543466835-00a7907e9de1?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OTV8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("tina", "chiquita", 1, 12.5f, "https://images.unsplash.com/photo-1543466835-00a7907e9de1?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OTV8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("tina", "chiquita", 1, 12.7f, "https://images.unsplash.com/photo-1512546321483-c0468b7b8a95?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OTB8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("tina", "chiquita", 1, 12.9f, "https://images.unsplash.com/photo-1490042706304-06c664f6fd9a?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OTl8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("tina", "chiquita", 1, 13.0f, "https://images.unsplash.com/photo-1504430225224-21a649ac91bc?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTAwfHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("lola", "mediana", 2, 14.5f, "https://images.unsplash.com/photo-1535230464639-a413d00b9934?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OTR8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("balto", "grande", 3, 30.0f, "https://images.unsplash.com/photo-1507660392550-9aff6e04c7e5?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTA0fHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("rambo", "grande", 3, 29.5f, "https://images.unsplash.com/photo-1518815068914-038920b6f0c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTA4fHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("odin", "grande", 3, 28.5f, "https://images.unsplash.com/photo-1478340669871-015d85cb965b?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTAyfHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("balto", "grande", 3, 31.0f, "https://images.unsplash.com/photo-1510020553968-30f966e1ec9e?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTEwfHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("rambo", "grande", 3, 30.5f, "https://images.unsplash.com/photo-1516222338250-863216ce01ea?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTExfHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("odin", "grande", 3, 32.0f, "https://plus.unsplash.com/premium_photo-1719177518229-79d47d45d49a?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTA1fHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("balto", "grande", 3, 33.0f, "https://images.unsplash.com/photo-1532353949707-2e77707ee8a7?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTEyfHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("rambo", "grande", 3, 31.5f, "https://plus.unsplash.com/premium_photo-1666229410352-c4686b71cea2?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTEzfHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("odin", "grande", 3, 34.0f, "https://images.unsplash.com/photo-1587300003388-59208cc962cb?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTA3fHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("balto", "grande", 3, 35.0f, "https://images.unsplash.com/photo-1583512603805-3cc6b41f3edb?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTE1fHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("rambo", "grande", 3, 33.5f, "https://plus.unsplash.com/premium_photo-1676389281733-aaefab0e7907?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTA5fHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("odin", "grande", 3, 36.0f, "https://images.unsplash.com/photo-1477884143921-51d0a574ee09?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTE5fHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("balto", "grande", 3, 37.0f, "https://plus.unsplash.com/premium_photo-1710406095492-7e62eba19745?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTIxfHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("rambo", "grande", 3, 35.5f, "https://images.unsplash.com/photo-1514373941175-0a141072bbc8?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTE0fHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("odin", "grande", 3, 38.0f, "https://images.unsplash.com/photo-1583511655826-05700d52f4d9?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTIzfHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("balto", "grande", 3, 39.0f, "https://plus.unsplash.com/premium_photo-1668208363137-7ebc4ce6b7b7?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTQxfHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("rambo", "grande", 3, 37.5f, "https://images.unsplash.com/photo-1499789853431-fcbf274f57b9?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTQyfHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("odin", "grande", 3, 40.0f, "https://images.unsplash.com/photo-1503324280674-50695c3ae35f?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTM4fHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("max", "grande", 3, 41.0f, "https://plus.unsplash.com/premium_photo-1666278379770-440439b08656?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTMzfHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("coco", "mediana", 2, 18.0f, "https://plus.unsplash.com/premium_photo-1722859221349-26353eae4744?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTI1fHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("bella", "chiquita", 1, 13.5f, "https://images.unsplash.com/photo-1514571948039-d3cb9e8f9750?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTIyfHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("lucky", "mediana", 2, 17.5f, "https://images.unsplash.com/photo-1513350660342-816d92510adf?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTI3fHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("rocky", "grande", 3, 42.0f, "https://images.unsplash.com/photo-1575859431774-2e57ed632664?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTE4fHxkb2d8ZW58MHx8MHx8fDA%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("nala", "mediana", 2, 19.0f, "https://images.unsplash.com/photo-1523480717984-24cba35ae1ef?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8ODh8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("duque", "grande", 3, 43.0f, "https://images.unsplash.com/photo-1521218597721-2e247daed5db?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8ODJ8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("simba", "grande", 3, 44.0f, "https://images.unsplash.com/photo-1507813686138-954c2c879e8d?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OTF8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("mia", "chiquita", 1, 14.0f, "https://images.unsplash.com/photo-1477973770766-6228305816df?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nzl8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("chispas", "mediana", 2, 18.5f, "https://images.unsplash.com/uploads/1412433710756bfa9ec14/d568362b?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8ODd8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("bruno", "grande", 3, 45.0f, "https://images.unsplash.com/photo-1506903536293-8419385acdce?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8ODN8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("luna", "mediana", 2, 20.0f, "https://images.unsplash.com/photo-1491930722919-a5fc9ad49a75?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8ODB8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("duque", "grande", 3, 46.0f, "https://images.unsplash.com/photo-1508948956644-0017e845d797?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8ODZ8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("sasha", "grande", 3, 47.0f, "https://images.unsplash.com/photo-1535930749574-1399327ce78f?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NzR8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("rocky", "grande", 3, 48.0f, "https://images.unsplash.com/photo-1472698938026-79bed881e5b7?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8ODR8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("toby", "mediana", 2, 21.0f, "https://images.unsplash.com/photo-1440500758936-9ec6bc937f5e?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nzh8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("kira", "grande", 3, 49.0f, "https://images.unsplash.com/photo-1497994139250-caecb78f9df9?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NzV8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("coco", "mediana", 2, 22.0f, "https://plus.unsplash.com/premium_photo-1666298864117-ffc36b8e0cc4?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nzd8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("zeus", "grande", 3, 50.0f, "https://images.unsplash.com/photo-1493916665398-143bdeabe500?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NzJ8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("thor", "grande", 3, 51.0f, "https://images.unsplash.com/photo-1508280756091-9bdd7ef1f463?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NzB8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("osito", "chiquita", 1, 15.0f, "https://images.unsplash.com/photo-1483434748604-140edba26886?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NzZ8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("duque", "grande", 3, 52.0f, "https://images.unsplash.com/photo-1497994187231-bc847a69dc76?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NjN8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("balto", "grande", 3, 53.0f, "https://images.unsplash.com/photo-1507146426996-ef05306b995a?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MzB8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("rambo", "grande", 3, 54.0f, "https://unsplash.com/photos/black-and-white-short-coated-dog-N04FIfHhv_k", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("odin", "grande", 3, 55.0f, "https://images.unsplash.com/photo-1516366434321-728a48e6b7bf?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MzJ8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("maya", "mediana", 2, 23.0f, "https://images.unsplash.com/photo-1552053831-71594a27632d?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjZ8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("bella", "chiquita", 1, 16.0f, "https://plus.unsplash.com/premium_photo-1669277330818-c5cc14e5dfea?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mjl8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("max", "grande", 3, 56.0f, "https://images.unsplash.com/photo-1516371535707-512a1e83bb9a?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mjh8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("kenia", "mediana", 2, 24.0f, "https://images.unsplash.com/photo-1491604612772-6853927639ef?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjN8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("luna", "mediana", 2, 25.0f, "https://plus.unsplash.com/premium_photo-1668257262252-05b4d1a438ae?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjV8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("thor", "grande", 3, 57.0f, "https://images.unsplash.com/photo-1452441271666-5d998aa2f6cc?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mjd8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("nina", "mediana", 2, 26.0f, "https://images.unsplash.com/photo-1453227588063-bb302b62f50b?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjB8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));
        mascotaRepository.save(new Mascota("duque", "grande", 3, 58.0f, "https://images.unsplash.com/photo-1510771463146-e89e6e86560e?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjJ8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "bien"));
        mascotaRepository.save(new Mascota("sasha", "grande", 3, 59.0f, "https://plus.unsplash.com/premium_photo-1667030783942-05351fd6c3fc?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjF8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D", "ninguna", "excelente"));

        veterinarioRepository.save(new Veterinario(24L, "V024", "pass456", "Medicina Felina", 115, "Dra. Patricia Gómez", 
        "https://www.shutterstock.com/image-photo/veterinarian-cute-dog-on-white-260nw-1538054498.jpg",null));
        veterinarioRepository.save(new Veterinario(1L, "V001", "pass123", "Cardiología", 100, "Dr. Juan Pérez", 
        "https://images.unsplash.com/photo-1532615863397-4a6b7ad973d1?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(2L, "V002", "pass234", "Dermatología", 120, "Dra. Ana López", 
        "https://images.unsplash.com/photo-1513244904703-e01f705cce44?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(3L, "V003", "pass345", "Odontología", 150, "Dr. Carlos Gómez", 
        "https://images.unsplash.com/photo-1517351683210-3d58f3e2a2e1?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(4L, "V004", "pass456", "Oncología", 80, "Dra. Laura Fernández", 
        "https://images.unsplash.com/photo-1542902322-28ae53d46f96?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(5L, "V005", "pass567", "Neurología", 90, "Dr. Jorge Ramírez", 
        "https://images.unsplash.com/photo-1509092276505-431b39b9b5cb?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(6L, "V006", "pass678", "Ortopedia", 110, "Dra. María Torres", 
        "https://images.unsplash.com/photo-1519133456630-d95ecedc58b0?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(7L, "V007", "pass789", "Reproducción", 130, "Dr. Luis Martínez", 
        "https://images.unsplash.com/photo-1555685818-5f3c5a18c637?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(8L, "V008", "pass890", "Medicina Interna", 140, "Dra. Isabel García", 
        "https://images.unsplash.com/photo-1606765086304-f69c4a774062?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(9L, "V009", "pass901", "Anestesiología", 75, "Dr. Sergio Vargas", 
        "https://images.unsplash.com/photo-1513050665636-8a8dbf6e885e?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(10L, "V010", "pass012", "Emergencias", 160, "Dra. Elena Ruiz", 
        "https://images.unsplash.com/photo-1566497625-fc589c781c83?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(11L, "V011", "pass123", "Geriatría", 95, "Dr. Roberto Morales", 
        "https://images.unsplash.com/photo-1543441330-4d717f8a2306?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(12L, "V012", "pass234", "Psiquiatría", 105, "Dra. Verónica Pérez", 
        "https://images.unsplash.com/photo-1529665213090-9b23559a5827?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(13L, "V013", "pass345", "Cirugía General", 200, "Dr. Felipe Gómez", 
        "https://images.unsplash.com/photo-1542255974-e0f3d2e1f865?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(14L, "V014", "pass456", "Medicina Preventiva", 85, "Dra. Patricia Fernández", 
        "https://images.unsplash.com/photo-1546454364-cb1eaf27f6c5?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(15L, "V015", "pass567", "Hematología", 70, "Dr. Álvaro López", 
        "https://images.unsplash.com/photo-1543278583-9dc6576581cb?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(16L, "V016", "pass678", "Neumología", 60, "Dra. Carmen Jiménez", 
        "https://images.unsplash.com/photo-1514294236627-3bb26d50c5c7?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(17L, "V017", "pass789", "Terapia Física", 110, "Dr. Nicolás Silva", 
        "https://images.unsplash.com/photo-1530972758556-79d3e7d95cb1?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(18L, "V018", "pass890", "Medicina Tropical", 125, "Dra. Julia Mendoza", 
        "https://images.unsplash.com/photo-1589397094520-bd226a15ff7e?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(19L, "V019", "pass901", "Gastroenterología", 95, "Dr. Guillermo Soto", 
        "https://images.unsplash.com/photo-1554963150-d2d8810aa6a8?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(20L, "V020", "pass012", "Medicina de Urgencias", 140, "Dra. Alejandra Ruiz", 
        "https://images.unsplash.com/photo-1525902959531-89114f1c51e4?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(21L, "V021", "pass123", "Toxicología", 80, "Dr. Ricardo Molina", 
        "https://images.unsplash.com/photo-1541672284511-28a74f012b94?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(22L, "V022", "pass234", "Parasitología", 100, "Dra. Gabriela González", 
        "https://images.unsplash.com/photo-1542239750-57942d4e5e04?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(23L, "V023", "pass345", "Medicina de Animales Exóticos", 65, "Dr. Martín Ortega", 
        "https://images.unsplash.com/photo-1575579643-5656494d5875?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(24L, "V024", "pass456", "Medicina Felina", 115, "Dra. Patricia Gómez", 
        "https://images.unsplash.com/photo-1531512970898-7f9d5ea5b7d7?w=500&auto=format&fit=crop&q=60", 
        null));

    veterinarioRepository.save(new Veterinario(25L, "V025", "pass567", "Medicina Canina", 140, "Dr. Hugo Martínez", 
        "https://images.unsplash.com/photo-1565070851-0a8b023e9448?w=500&auto=format&fit=crop&q=60", 
        null));


        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Mascota> mascotas = mascotaRepository.findAll();

        for (int i = 0; i < mascotas.size(); i++) {
            Mascota mascota = mascotas.get(i);
            Usuario usuario = usuarios.get(i % usuarios.size()); // Asociación cíclica
            
            // Establecer la relación en ambos lados
            mascota.setUsuario(usuario);
            usuario.getMascotas().add(mascota);

            // Guardar ambos
            usuarioRepository.save(usuario);
            mascotaRepository.save(mascota);
        }

    //Cargar excel

                InputStream inputStream = getClass().getResourceAsStream("/static/excel/MEDICAMENTOS_VETERINARIA.xlsx");
        
                // Leer el archivo Excel
                Workbook workbook = new XSSFWorkbook(inputStream);
                Sheet sheet = workbook.getSheetAt(0); // Leer la primera hoja

                // Iterar sobre las filas del archivo
                Iterator<Row> rows = sheet.iterator();

                while (rows.hasNext()) {
                        Row row = rows.next();

                        // Suponiendo que la primera fila contiene encabezados
                        if (row.getRowNum() == 0) {
                                continue; // Omitir encabezados
                        }

                        // Suponiendo que el archivo tiene las columnas: nombre, dosis, descripción,
                        // etc.
                        String nombre = row.getCell(0).getStringCellValue();
                        double precioVenta = row.getCell(1).getNumericCellValue();
                        double precioCompra = row.getCell(2).getNumericCellValue();
                        int unidadesDisponibles = (int) row.getCell(3).getNumericCellValue();
                        int unidadesVendidas = (int) row.getCell(4).getNumericCellValue();
                        // Otros campos que puedas tener...

                        // Crear una nueva instancia de Droga
                        Medicamento droga = new Medicamento();
                        droga.setNombre(nombre);
                        droga.setPrecio_venta(precioVenta);
                        droga.setPrecio_compra(precioCompra);
                        droga.setUnidades_disponibles(unidadesDisponibles);
                        droga.setUnidades_vendidas(unidadesVendidas);
                        // Asignar otros campos...

                        // Guardar el medicamento en la base de datos
                        medicamentoRepository.save(droga);
                }

                // Cerrar el workbook y el InputStream
                workbook.close();
                inputStream.close();

        //cREAR LOS 10 TRATAMIENTOS

        List<Veterinario> veterinarios = veterinarioRepository.findAll();
        List<Medicamento> medicamentos = medicamentoRepository.findAll();

        Calendar calendar = Calendar.getInstance();

        // Asegúrate de que los índices para veterinarios, medicamentos y mascotas son válidos.
        calendar.set(2023, Calendar.MAY, 10);
        tratamientoRepository.save(new Tratamiento(calendar.getTime(), 11.0f, mascotas.get(0), veterinarios.get(0), Arrays.asList(medicamentos.get(0))));

        calendar.set(2023, Calendar.JUNE, 15);
        tratamientoRepository.save(new Tratamiento(calendar.getTime(), 12.5f, mascotas.get(1), veterinarios.get(1), Arrays.asList(medicamentos.get(1))));

        calendar.set(2023, Calendar.JULY, 20);
        tratamientoRepository.save(new Tratamiento(calendar.getTime(), 10.0f, mascotas.get(2), veterinarios.get(2), Arrays.asList(medicamentos.get(2))));

        calendar.set(2023, Calendar.AUGUST, 25);
        tratamientoRepository.save(new Tratamiento(calendar.getTime(), 15.0f, mascotas.get(3), veterinarios.get(0), Arrays.asList(medicamentos.get(1), medicamentos.get(3))));

        calendar.set(2023, Calendar.SEPTEMBER, 30);
        tratamientoRepository.save(new Tratamiento(calendar.getTime(), 13.0f, mascotas.get(4), veterinarios.get(1), Arrays.asList(medicamentos.get(0), medicamentos.get(2))));

        calendar.set(2023, Calendar.OCTOBER, 5);
        tratamientoRepository.save(new Tratamiento(calendar.getTime(), 9.0f, mascotas.get(5), veterinarios.get(2), Arrays.asList(medicamentos.get(1))));

        calendar.set(2023, Calendar.NOVEMBER, 10);
        tratamientoRepository.save(new Tratamiento(calendar.getTime(), 8.5f, mascotas.get(0), veterinarios.get(0), Arrays.asList(medicamentos.get(2))));

        calendar.set(2023, Calendar.DECEMBER, 15);
        tratamientoRepository.save(new Tratamiento(calendar.getTime(), 14.0f, mascotas.get(1), veterinarios.get(1), Arrays.asList(medicamentos.get(0), medicamentos.get(3))));

        calendar.set(2024, Calendar.JANUARY, 20);
        tratamientoRepository.save(new Tratamiento(calendar.getTime(), 17.0f, mascotas.get(2), veterinarios.get(2), Arrays.asList(medicamentos.get(0))));

        calendar.set(2024, Calendar.FEBRUARY, 25);
        tratamientoRepository.save(new Tratamiento(calendar.getTime(), 19.0f, mascotas.get(3), veterinarios.get(0), Arrays.asList(medicamentos.get(1), medicamentos.get(3))));
    }
}