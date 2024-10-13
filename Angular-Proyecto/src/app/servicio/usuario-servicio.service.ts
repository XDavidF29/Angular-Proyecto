import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Mascota } from '../models/Mascota';
import { Usuario } from '../models/Usuario'; // Asegúrate de ajustar la ruta según tu estructura de proyecto

@Injectable({
  providedIn: 'root'
})
export class UsuarioServicioService {
  constructor(private http: HttpClient) { 
  }
    // Retorna todos los usuarios
  findAll(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`http://localhost:8090/usuario/all`);
  }

  // Busca un usuario por su id
  findById(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(`http://localhost:8090/usuario/find/${id}`);
  }

  // Actualiza un usuario existente
  update(usuario: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>("http://localhost:8090/usuario/update/${usuario.id}", usuario);
  }

  // Elimina un usuario por su id
delete(id: number): Observable<void> {
  return this.http.delete<void>(`http://localhost:8090/usuario/delete/${id}`);
}

  // Agregar una nueva usuario
  addUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(`http://localhost:8090/usuario/add`, usuario);
  }

  findMascotasByUsuarioId(usuarioId: number): Observable<Mascota[]> {
    return this.http.get<Mascota[]>(`http://localhost:8090/usuario/${usuarioId}/mascotas`); // Ajusta la URL según tu API
  }

}
