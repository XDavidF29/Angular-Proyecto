import { Component } from '@angular/core';
import { Router } from '@angular/router'; // Para redirigir
import { Usuario } from '../models/Usuario';
import { UsuarioServicioService } from '../servicio/usuario-servicio.service';

@Component({
  selector: 'app-login-usuario',
  templateUrl: './login-usuario.component.html',
  styleUrls: ['./login-usuario.component.css']
})
export class LoginUsuarioComponent {
  cedula: number = 0;
  error: string = '';

  constructor(private usuarioService: UsuarioServicioService, private router: Router) {}

  login() {

    const usuario = {
      cedula: this.cedula,
    } as Usuario;

    this.usuarioService.loginUsuario(usuario).subscribe({
      next: (usuario: Usuario) => {
        // Redirigir a la página de detalles del usuario
        this.router.navigate(['/usuario/find/', usuario.id]);
      },
      error: (err) => {
        this.error = 'Cédula incorrecta o usuario no encontrado';
        this.cedula = 0;
      }
    });
  }
}