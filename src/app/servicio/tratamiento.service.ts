import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tratamiento } from '../models/Tratamiento'; // Asegúrate de ajustar la ruta según tu estructura de proyecto

@Injectable({
  providedIn: 'root'
})
export class TratamientoService {
  constructor(private http: HttpClient) { 
  }
    // Retorna todos los veterinarios
  findAll(): Observable<Tratamiento[]> {
    return this.http.get<Tratamiento[]>(`http://localhost:8090/tratamiento/all`);
  }

  // Retorna un tratamiento por ID
  findById(id: number): Observable<Tratamiento> {
    return this.http.get<Tratamiento>(`http://localhost:8090/tratamiento/find/${id}`);
  }
 
}
