import { Component } from '@angular/core';
import { Router } from '@angular/router'; // Para redirigir
import { Veterinario } from '../models/Veterinario'; // Asegúrate de que la ruta sea correcta
import { VeterinarioServicioService } from '../servicio/veterinario-servicio.service';

@Component({
  selector: 'app-login-veterinario',
  templateUrl: './login-veterinario.component.html',
  styleUrls: ['./login-veterinario.component.css']
})
export class LoginVeterinarioComponent {
  cedula: string = '';  // Cambiado a string para coincidir con el tipo esperado
  contrasena: string = '';
  error: string = '';

  constructor(private veterinarioService: VeterinarioServicioService, private router: Router) {}

  login() {
    // Crear el objeto veterinario con la cédula y la contraseña
    const veterinario = {
      cedula: this.cedula,
      password: this.contrasena
    } as Veterinario;

    
    this.veterinarioService.loginVeterinario(veterinario).subscribe({
      next: (veterinario: Veterinario) => {
        // Redirigir a la página de detalles del usuario
        this.router.navigate(['/veterinario/find/', veterinario.id]);
      },
      error: (err) => {
        this.error = 'Cédula o contraseña incorrecta';
        this.cedula = '';
        this.contrasena = '';
      }
    });
  }
}
