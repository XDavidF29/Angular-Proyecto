import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Mascota } from '../models/Mascota';
import { MascotaServicioService } from '../servicio/mascota-servicio.service';

@Component({
  selector: 'app-crear-mascota',
  templateUrl: './crear-mascota.component.html',
  styleUrls: ['./crear-mascota.component.css']
})
export class CrearMascotaComponent {
  nuevaMascota: Mascota = {
    id: 0,
    nombre: '',
    raza: '',
    edad: 0,
    peso: 0,
    foto: '',
    enfermedad: '',
    estado: 'Saludable',
    usuario: {
      cedula: 0,
    }
  };

  constructor(private mascotaServicio: MascotaServicioService) {}

  addMascota(form: NgForm) {
    if (form.valid) {
      // Verificación adicional de que la cédula no sea 0 o vacía
      if (this.nuevaMascota.usuario.cedula <= 0) {
        alert('Debe ingresar una cédula válida para el usuario.');
        return;
      }

      this.mascotaServicio.addMascota(this.nuevaMascota).subscribe(
        () => {
          alert('Mascota registrada exitosamente.');
          form.reset(); // Limpiar el formulario después de registrar
        },
        error => {
          console.error('Error al registrar la mascota:', error);
          // Mostrar detalles del error en la alerta
          alert(`Error al registrar la mascota: ${error.message || 'Error desconocido'}`);
        }
      );
      
    }
  }  
}
  