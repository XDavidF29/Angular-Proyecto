import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router'; // Importar Router
import { Mascota } from '../models/Mascota';
import { MascotaServicioService } from '../servicio/mascota-servicio.service';

@Component({
  selector: 'app-crear-mascota',
  templateUrl: './crear-mascota.component.html',
  styleUrls: ['./crear-mascota.component.css']
})
export class CrearMascotaComponent implements OnInit {
  nuevaMascota: Mascota = {
    id: 0,
    nombre: '',
    raza: '',
    edad: 0,
    peso: 0,
    foto: '',
    enfermedad: '',
    estado: 'Activo',
    usuario: {
      cedula: 0,
    }
  };

  mensajeError: string = ''; // Variable para almacenar errores

  constructor(
    private mascotaServicio: MascotaServicioService, 
    private route: ActivatedRoute,
    private router: Router // Inyectar Router
  ) {}

  ngOnInit() {
    // Obtener la cédula del usuario desde los parámetros de la ruta
    this.route.params.subscribe(params => {
      if (params['cedula?']) {
        this.nuevaMascota.usuario.cedula = +params['cedula?']; // Convertir a número
      }
    });
  }

  addMascota(form: NgForm) {
    this.mensajeError = '';

    if (form.valid) {
      // Verificación adicional de que la cédula no sea 0 o vacía
      if (this.nuevaMascota.usuario.cedula <= 0) {
        this.mensajeError = 'Debe ingresar una cédula válida para el usuario.';
        return;
      }

      // Llamada al servicio para agregar mascota
      this.mascotaServicio.addMascota(this.nuevaMascota).subscribe({
        next: (response:Mascota) => {
          // Redireccionar a la página de detalles de la mascota
          this.router.navigate(['/mascota/find/', response.id]);
        },
        error: (err) => {
          console.error('Error al registrar la mascota:', err);
          this.mensajeError = `Error al registrar la mascota. Inténtelo de nuevo.`;
        }
      });
    }
  }
}
