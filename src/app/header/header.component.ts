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

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.isLoggedIn$.subscribe((isLoggedIn) => {
      this.isLoggedIn = isLoggedIn;
    });
  }

  // Método para cerrar sesión
  logout(): void {
    this.authService.logout();
    this.isLoggedIn = false;
    // También podrías redirigir al usuario si es necesario
  }
}

