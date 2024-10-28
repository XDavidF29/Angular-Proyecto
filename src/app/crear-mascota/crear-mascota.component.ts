import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Mascota } from '../models/Mascota';
import { Usuario } from '../models/Usuario'; // Asegúrate de importar el modelo de Usuario
import { MascotaServicioService } from '../servicio/mascota-servicio.service';
import { UsuarioServicioService } from '../servicio/usuario-servicio.service'; // Importa el servicio de Usuario

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

    usuarios: Usuario[] = []; // Lista completa de usuarios
    usuariosFiltrados: Usuario[] = []; // Usuarios filtrados
    mostrarSugerencias: boolean = false; // Controla la visibilidad de las sugerencias
    mensajeError: string = ''; // Variable para almacenar errores

    constructor(
        private mascotaServicio: MascotaServicioService,
        private usuarioServicio: UsuarioServicioService, // Inyecta el servicio de usuarios
        private route: ActivatedRoute,
        private router: Router
    ) {}

    ngOnInit() {
        // Obtener la cédula del usuario desde los parámetros de la ruta
        this.route.params.subscribe(params => {
            if (params['cedula?']) {
                this.nuevaMascota.usuario.cedula = +params['cedula?'];
            }
        });

        // Cargar la lista de usuarios al inicio
        this.usuarioServicio.findAll().subscribe((usuarios: Usuario[]) => {
            this.usuarios = usuarios;
        });
    }

    filtrarUsuarios(): void {
      const busqueda = this.nuevaMascota.usuario.cedula.toString();
      if(busqueda) {

      this.usuariosFiltrados = this.usuarios.
        filter(usuario => usuario.cedula.toString().includes(busqueda))
        .slice(0, 4);
        this.mostrarSugerencias = this.usuariosFiltrados.length > 0; // Mostrar sugerencias si hay resultados
      } else {
            this.mostrarSugerencias = false; // Ocultar si no hay texto
      }
    }

    seleccionarUsuario(usuario: Usuario): void {
      this.nuevaMascota.usuario.cedula = usuario.cedula; // Asigna la cédula seleccionada
      this.usuariosFiltrados = []; // Limpia las sugerencias
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
                next: (response: Mascota) => {
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
