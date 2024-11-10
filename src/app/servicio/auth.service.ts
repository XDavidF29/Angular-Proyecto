// auth.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly TOKEN_KEY = 'authToken';
  private isLoggedInSubject = new BehaviorSubject<boolean>(this.hasToken());

  // Observable para que otros componentes escuchen los cambios en el estado de autenticación
  isLoggedIn$ = this.isLoggedInSubject.asObservable();

  constructor() {}

  private hasToken(): boolean {
    return !!localStorage.getItem('token');
  }

  login(token: string, role: string): void {
    localStorage.setItem('token', token);
    localStorage.setItem('role', role);
    this.isLoggedInSubject.next(true); // Emitir que el usuario está logueado
  }

  logout(): void {
    localStorage.removeItem('token');
    this.isLoggedInSubject.next(false); // Emitir que el usuario se ha deslogueado
  }
}
