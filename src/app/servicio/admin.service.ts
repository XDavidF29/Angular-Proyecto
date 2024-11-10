import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admin } from '../models/Admin';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) {}

  // Obtener todas las estadísticas del dashboard en una sola llamada
  getDashboardData(): Observable<any> {
    return this.http.get<any>(`http://localhost:8090/admin/dashboard`);
  }

  loginAdmin(user: Admin): Observable<String> {
    return this.http.post('http://localhost:8090/admin/login', user, {
      responseType: 'text',
    });
  }
}
