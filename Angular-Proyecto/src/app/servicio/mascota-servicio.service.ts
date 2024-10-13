import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Mascota } from '../models/Mascota';

@Injectable({
  providedIn: 'root'
})
export class MascotaServicioService {

  constructor(private http: HttpClient) { 
  }
    // Retorna todas las mascotas
  findAll(): Observable<Mascota[]> {
    return this.http.get<Mascota[]>("http://localhost:8090/mascota/all");
  }

  // Busca una mascota por su id
  findById(id: number): Observable<Mascota> {
    return this.http.get<Mascota>(`http://localhost:8090/mascota/find/${id}`);
  }

// Actualiza una mascota existente
  update(mascota: Mascota): Observable<Mascota> {
    return this.http.put<Mascota>(`http://localhost:8090/mascota/update/${mascota.id}`, mascota);
  }


  // Elimina una mascota por su id
  /*delete(id: number) {
    this.http.delete("http://localhost:8090/mascota/delete"+id).subscribe();
  } */
  delete(id: number): Observable<void> {
      return this.http.delete<void>(`http://localhost:8090/mascota/delete/${id}`);
  }

  // Agregar una nueva mascota
  addMascota(mascota: Mascota): Observable<Mascota> {
    return this.http.post<Mascota>(`http://localhost:8090/mascota/add`, mascota);
  }
}
