import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
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

  constructor(private veterinarioServicio: VeterinarioServicioService) {}

  addVeterinario(form: NgForm) {
    if (form.valid) {

      console.log('Datos del Veterinario a enviar:', this.nuevoVeterinario); // Verificar los datos

      this.veterinarioServicio.addveterinario(this.nuevoVeterinario).subscribe(
        () => {
          alert('Veterinario registrado exitosamente.');
          form.reset(); // Limpiar el formulario después de registrar
        },
        error => {
          console.error('Error al registrar el Veterinario:', error);
          alert('Error al registrar el Veterinario.');
        }
      );
    }
  }
}
