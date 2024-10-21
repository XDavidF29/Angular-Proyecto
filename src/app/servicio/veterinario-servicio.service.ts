import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Mascota } from '../models/Mascota';
import { Tratamiento} from  '../models/Tratamiento';
import { Veterinario } from '../models/Veterinario'; // Asegúrate de ajustar la ruta según tu estructura de proyecto

@Injectable({
  providedIn: 'root'
})
export class VeterinarioServicioService {
  constructor(private http: HttpClient) { 
  }
    // Retorna todos los veterinarios
  findAll(): Observable<Veterinario[]> {
    return this.http.get<Veterinario[]>(`http://localhost:8090/veterinario/all`);
  }

  // Busca un veterinario por su id
  findById(id: number): Observable<Veterinario> {
    return this.http.get<Veterinario>(`http://localhost:8090/veterinario/find/${id}`);
  }

  // Actualiza un veterinario existente
  update(veterinario: Veterinario): Observable<Veterinario> {
    return this.http.put<Veterinario>("http://localhost:8090/veterinario/update/${veterinario.id}", veterinario);
  }

  // Elimina un veterinario por su id
  delete(id: number): Observable<void> {
  return this.http.delete<void>(`http://localhost:8090/veterinario/delete/${id}`);
}

  // Agregar una nueva veterinario
  addveterinario(veterinario: Veterinario): Observable<Veterinario> {
    return this.http.post<Veterinario>(`http://localhost:8090/veterinario/add`, veterinario);
  }

  findMascotasByveterinarioId(veterinarioId: number): Observable<Mascota[]> {
    return this.http.get<Mascota[]>(`http://localhost:8090/veterinario/${veterinarioId}/mascotas`); // Ajusta la URL según tu API
  }

  // Busca los tratamientos de un veterinario por su id
  findTratamientosByVeterinarioId(veterinarioId: number): Observable<Tratamiento[]> {
    return this.http.get<Tratamiento[]>(`http://localhost:8090/veterinario/${veterinarioId}/tratamientos`);
  }

  // Accede a la información del veterinario en el login
  loginVeterinario(cedula: number, contrasena: string): Observable<Veterinario> {
    return this.http.get<Veterinario>(`http://localhost:8090/veterinario/login?cedula=${cedula}&contrasena=${contrasena}`);
  }


  buscarVeterinarios(nombre: string): Observable<Veterinario[]> {
    return this.http.get<Veterinario[]>(`http://localhost:8090/veterinario/buscar?nombre=${nombre}`);
  }
}
