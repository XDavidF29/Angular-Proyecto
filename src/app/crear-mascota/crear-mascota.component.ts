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
    usuarioId: 0
  };

  constructor(private mascotaServicio: MascotaServicioService) {}

  // Método para añadir una mascota
  addMascota(form: NgForm) {
    if (form.valid) {
      this.mascotaServicio.addMascota(this.nuevaMascota); // Usar el servicio para añadir
      form.reset(); // Limpiar el formulario
      alert('Mascota registrada exitosamente.');
    }
  }
}
