import { Component } from '@angular/core';
import { Router } from '@angular/router'; // Para redirigir
import { Usuario } from '../models/Usuario';
import { UsuarioServicioService } from '../servicio/usuario-servicio.service';
import { User } from '../models/user';

@Component({
  selector: 'app-login-usuario',
  templateUrl: './login-usuario.component.html',
  styleUrls: ['./login-usuario.component.css']
})
export class LoginUsuarioComponent {
  constructor(private usuarioService: UsuarioServicioService, private router: Router) {}
  error: string = '';

  formUser:User = {
    cedula: '',
    password: ''
  };


  login(form:any) {

    this.usuarioService.loginUsuario(this.formUser).subscribe({
      next: (data) => {
        // Redirigir a la página de detalles del usuario
        localStorage.setItem('token', String(data));
        this.router.navigate(['/usuario/home']);
      },
      error: (err) => {
        this.error = 'Cédula incorrecta o usuario no encontrado';
      }
    });
  }
}