import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Mascota } from '../models/Mascota';
import { Tratamiento } from '../models/Tratamiento';

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

// Asignar un tratamiento, incluyendo la cédula del veterinario
addTratamiento(mascotaId: number, tratamiento: Tratamiento, cedulaVeterinario: string): Observable<Mascota> {
  const url = `http://localhost:8090/mascota/tratamiento/${mascotaId}`;
  const params = { cedulaVeterinario };  // Parámetro de la cédula del veterinario

  // Aquí enviamos el tratamiento en el cuerpo de la solicitud
  return this.http.post<Mascota>(url, tratamiento, { params });
}

buscarMascotas(nombre: string): Observable<Mascota[]> {
  return this.http.get<Mascota[]>(`http://localhost:8090/mascota/buscar?nombre=${nombre}`);
}

}
