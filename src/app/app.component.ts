import { Component } from '@angular/core';
import { Router } from '@angular/router'; // Inyecta el router

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'proyectoAngular';

  constructor(public router: Router) {}  // Inyéctalo aquí
}
