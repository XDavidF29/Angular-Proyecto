import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
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

  constructor(private usuarioServicio: UsuarioServicioService) {}

  addUsuario(form: NgForm) {
    if (form.valid) {
      // Verificación de la cédula
      if (this.nuevoUsuario.cedula <= 0) {
        alert('Debe ingresar una cédula válida.');
        return;
      }

      console.log('Datos del Usuario a enviar:', this.nuevoUsuario); // Verificar los datos

      this.usuarioServicio.addUsuario(this.nuevoUsuario).subscribe(
        () => {
          alert('Usuario registrado exitosamente.');
          form.reset(); // Limpiar el formulario después de registrar
        },
        error => {
          console.error('Error al registrar el Usuario:', error);
          alert('Error al registrar el Usuario.');
        }
      );
    }
  }  
}
