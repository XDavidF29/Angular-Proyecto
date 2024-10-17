import { Component } from '@angular/core';
import { Router } from '@angular/router'; // Para redirigir
import { Veterinario } from '../models/Veterinario';
import { VeterinarioServicioService } from '../servicio/veterinario-servicio.service';

@Component({
  selector: 'app-login-veterinario',
  templateUrl: './login-veterinario.component.html',
  styleUrls: ['./login-veterinario.component.css']
})
export class LoginVeterinarioComponent {
  cedula: number = 0;
  error: string = '';

  constructor(private veterinarioService: VeterinarioServicioService, private router: Router) {}

  login() {
    this.veterinarioService.loginVeterinario(this.cedula).subscribe({
      next: (veterinario: Veterinario) => {
        // Redirigir a la página de detalles del usuario
        this.router.navigate(['/veterinario/find/', veterinario.id]);
      },
      error: (err) => {
        this.error = 'Cédula incorrecta o usuario no encontrado';
      }
    });
  }
}