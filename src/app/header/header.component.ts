import { Component, OnInit, Inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../servicio/auth.service'; // Adjust the path as necessary

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLoggedIn: boolean = false;
  userRole: string = localStorage.getItem('role') || ''; // Add this line to define the userRole property

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.authService.isLoggedIn$.subscribe((isLoggedIn) => {
      this.isLoggedIn = isLoggedIn;
    });

    this.authService.userRole$.subscribe((role) => {
      this.userRole = role;
    });
  }

  // Método para cerrar sesión
  logout(): void {
    this.authService.logout();
    this.isLoggedIn = false;
    // También podrías redirigir al usuario si es necesario
  }

  goToProfile(): void {
    if (this.userRole === 'Usuario') {
      this.router.navigate(['/usuario/home']);
    } else if (this.userRole === 'Veterinario') {
      this.router.navigate(['/veterinario/home']);
    } else if (this.userRole === 'Admin') {
      this.router.navigate(['/admin/dashboard']);
    }
  }
}

