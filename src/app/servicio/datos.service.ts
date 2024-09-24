import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Usuario } from '../models/Usuario';  // Asegúrate de ajustar la ruta según tu estructura de proyecto

@Injectable({
  providedIn: 'root'
})
export class DatosService {

  constructor() { }

  // Información quemada de usuarios
  private usuarios: Usuario[] = [
    {
      id: 1,
      nombre: 'Juan Pérez',
      correo: 'juan@example.com',
      celular: 123456789,
      cedula: 123456,
      mascotas: []
    },
    {
      id: 2,
      nombre: 'Ana Gómez',
      correo: 'ana@example.com',
      celular: 987654321,
      cedula: 654321,
      mascotas: []
    }
  ];

  // Método para obtener los usuarios quemados
  getUsuarios(): Observable<Usuario[]> {
    return of(this.usuarios);  // Retorna los datos como un Observable
  }
}
