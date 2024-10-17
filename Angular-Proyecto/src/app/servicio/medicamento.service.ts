import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Medicamento } from '../models/Medicamento';

@Injectable({
  providedIn: 'root'
})
export class MedicamentoService {

  private apiUrl = 'http://localhost:8090/medicamento';  // URL del backend

  constructor(private http: HttpClient) { }

  // Obtener todos los medicamentos
  findAll(): Observable<Medicamento[]> {
    return this.http.get<Medicamento[]>(`${this.apiUrl}/all`);
  }

  // Obtener un medicamento por su ID
  findById(id: number): Observable<Medicamento> {
    return this.http.get<Medicamento>(`${this.apiUrl}/find?id=${id}`);
  }

  // Agregar un nuevo medicamento
  add(medicamento: Medicamento): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/add`, medicamento);
  }

  // Actualizar un medicamento existente
  update(id: number, medicamento: Medicamento): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/update/${id}`, medicamento);
  }

  // Eliminar un medicamento por su ID
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
