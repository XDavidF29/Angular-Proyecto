package com.example.demo.controlador;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.UsuarioRepository;
import com.example.demo.repositorio.VeterinarioRepository;

@CrossOrigin(origins = "http://localhost:4200") // Permitir peticiones desde Angular
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    MascotaRepository mascotaRepository;
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    VeterinarioRepository veterinarioRepository;

    @Autowired
    TratamientoRepository tratamientoRepository;

    // Consolidar todas las estadísticas en una sola respuesta
    @GetMapping("/dashboard")
    public Map<String, Object> obtenerDashboardCompleto() {
        Map<String, Object> dashboardData = new HashMap<>();
        
        // Agregar atenciones por veterinario
        List<Object[]> atencionesVeterinarios = veterinarioRepository.obtenerAtencionesPorVeterinario();
        dashboardData.put("atencionesVeterinarios", atencionesVeterinarios);
        
        // Agregar estadísticas de mascotas
        List<Object[]> estadisticasMascotas = mascotaRepository.obtenerEstadisticasMascotas();
        dashboardData.put("estadisticasMascotas", estadisticasMascotas);
        
        // Contar la cantidad de mascotas totales
        dashboardData.put("mascotasTotales", mascotaRepository.contarMascotasTotales());
        
        // Ganancias totales de la veterinaria
        dashboardData.put("gananciasTotales", tratamientoRepository.calcularGananciasTotales());

        // Top 3 tratamientos con más unidades vendidas
        dashboardData.put("top3Tratamientos", tratamientoRepository.obtenerTop3Tratamientos());

        // Contar los tratamientos del ultimo mes
        LocalDate haceUnMes = LocalDate.now().minusDays(30);
        java.sql.Date fechaInicio = java.sql.Date.valueOf(haceUnMes); // Conversión a java.sql.Date
        dashboardData.put("tratamientosUltimoMes", tratamientoRepository.contarTratamientosUltimoMes(fechaInicio));
    
        //Contar tratamiento por medicamento ultimo mes
        //dashboardData.put("tratamientoPorMedicamentoUltimoMes", tratamientoRepository.contarTratamientosPorMedicamentoUltimoMes(fechaInicio));
    
        return dashboardData;  // Se retorna un mapa con toda la información
    }
}
