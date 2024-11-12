import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router'; // Importar Router
import { Veterinario } from '../models/Veterinario'; // Importa el modelo Veterinario
import { VeterinarioServicioService } from '../servicio/veterinario-servicio.service'; // Importa el servicio de Veterinario

@Component({
  selector: 'app-crear-veterinario',
  templateUrl: './crear-veterinario.component.html',
  styleUrls: ['./crear-veterinario.component.css']
})
export class CrearVeterinarioComponent {
  nuevoVeterinario: Veterinario = {
    id: 0,
    nombre: '',
    especialidad: '',
    foto: '',
    tratamientos: [],
    cedula: '',
    password: '',
    atenciones: 0
  };

  mensajeError: string = ''; // Variable para manejar mensajes de error

  constructor(private veterinarioServicio: VeterinarioServicioService, private router: Router) {} // Inyectar Router

  addVeterinario(form: NgForm) {
    this.mensajeError = ''; // Reiniciar mensaje de error

    // Agregar veterinario
    this.veterinarioServicio.addveterinario(this.nuevoVeterinario).subscribe({
      next: (response: Veterinario) => {
        console.log('Veterinario creado:', response);
        this.router.navigate(['/veterinario/find/', response.id], { replaceUrl: true }); // Redirigir al veterinario creado
      },
      error: (err) => {
        console.error('Error al registrar el Veterinario:', err);
        this.mensajeError = 'Error al registrar el Veterinario. Inténtelo de nuevo.'; // Mostrar mensaje de error
        this.limpiarCampos(); // Limpiar campos en caso de error
      }
    });
  }

  limpiarCampos() {
    this.nuevoVeterinario = {
      id: 0,
      nombre: '',
      especialidad: '',
      foto: '',
      tratamientos: [],
      cedula: '',
      password: '',
      atenciones: 0
    };
  }
}
