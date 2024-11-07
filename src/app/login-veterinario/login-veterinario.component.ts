import { Component } from '@angular/core';
import { Router } from '@angular/router'; // Para redirigir
import { Veterinario } from '../models/Veterinario'; // Asegúrate de que la ruta sea correcta
import { VeterinarioServicioService } from '../servicio/veterinario-servicio.service';
import { User } from '../models/user';

@Component({
  selector: 'app-login-veterinario',
  templateUrl: './login-veterinario.component.html',
  styleUrls: ['./login-veterinario.component.css']
})
export class LoginVeterinarioComponent {
  constructor(private veterinarioService: VeterinarioServicioService, private router: Router) {}
  cedula: string = '';  // Cambiado a string para coincidir con el tipo esperado
  contrasena: string = '';
  error: string = '';

  formUser:User = {
    cedula: '',
    password: ''
  };


  login() {

    this.veterinarioService.loginVeterinario(this.formUser).subscribe({
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
