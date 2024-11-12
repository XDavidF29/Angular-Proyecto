import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router'; // Importar Router
import { Usuario } from '../models/Usuario';
import { UsuarioServicioService } from '../servicio/usuario-servicio.service';
import { AuthService } from '../servicio/auth.service'; // Import AuthService

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
    mascotas: [],
  };

  mensajeError: string = '';
  constructor(private usuarioServicio: UsuarioServicioService, private router: Router, private authService: AuthService) {} 

  addUsuario(form: NgForm) {
    this.mensajeError = ''; 
  
    // Registrar al usuario
    this.usuarioServicio.addUsuario(this.nuevoUsuario).subscribe({
      next: (response: Usuario) => {
        console.log('Usuario registrado:', response);
        const credentials = {
          cedula: String(response.cedula),
          password: '' // Usa la contraseña proporcionada
        };
        console.log('Credentials:', credentials)
        this.usuarioServicio.loginUsuario(credentials).subscribe({
          next: (data) => {
            console.log('Inicio de sesión exitoso:', data);
            this.authService.login(String(data),'Usuario');
            this.router.navigate(['/usuario/home'], { replaceUrl: true }); // Redirigir a la página de inicio de usuario
          },
          error: (err) => {
            console.error('Error al iniciar sesión:', err);
            this.mensajeError = 'Error al iniciar sesión después del registro.';
          }
        });
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
