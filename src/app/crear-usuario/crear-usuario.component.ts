import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router'; // Importar Router
import { Usuario } from '../models/Usuario';
import { UsuarioServicioService } from '../servicio/usuario-servicio.service';

@Component({
  selector: 'app-crear-usuario',
  templateUrl: './crear-usuario.component.html',
  styleUrls: ['./crear-usuario.component.css']
})
export class CrearUsuarioComponent {
  nuevoUsuario: Usuario = {
    id: 0,
    nombre: '',
    correo: '',
    celular: 0,
    cedula: 0,
    mascotas: []
  };

  mensajeError: string = '';

  constructor(private usuarioServicio: UsuarioServicioService, private router: Router) {} // Inyectar Router

  addUsuario(form: NgForm) {
    this.mensajeError = ''; 

    // Agregar usuario
    this.usuarioServicio.addUsuario(this.nuevoUsuario).subscribe({
      next: (response: Usuario) => {
        this.router.navigate(['/usuario/find/', response.id]);
      },
      error: (err) => {
        console.error('Error al registrar el Usuario:', err);
        this.mensajeError = 'Error al registrar el Usuario. Inténtelo de nuevo.';
        this.limpiarCampos();
      }
    });
  }

  
  limpiarCampos() {
    this.nuevoUsuario = {
      id: 0,
      nombre: '',
      correo: '',
      celular: 0,
      cedula: 0,
      mascotas: []
    };
  }
}
