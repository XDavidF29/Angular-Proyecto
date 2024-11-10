import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { VeterinarioServicioService } from '../servicio/veterinario-servicio.service';
import { AdminService } from '../servicio/admin.service';
import { AuthService } from '../servicio/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsuarioServicioService } from '../servicio/usuario-servicio.service';

@Component({
  selector: 'app-login-veterinario',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  selectedType: string = ''; // Guardar el tipo de usuario seleccionado
  error: string = '';

  constructor(
    private fb: FormBuilder,
    private veterinarioService: VeterinarioServicioService,
    private adminService: AdminService,
    private usuarioService: UsuarioServicioService,
    private router: Router,
    private authService: AuthService
  ) {
    this.loginForm = this.fb.group({
      type: ['', Validators.required],
      cedulaVeterinario: [''],
      passwordVet: [''],
      usuario: [''],
      password: [''],
      cedula: ['']
    });
  }

  // Cambiar validaciones según el tipo de usuario
  onUserTypeChange(event: any) {
    this.selectedType = event.target.value;

    // Limpiar validaciones actuales
    this.clearValidations();

    // Aplicar validaciones según el tipo de usuario seleccionado
    if (this.selectedType === '1') {
      this.loginForm.get('cedulaVeterinario')?.setValidators([Validators.required]);
      this.loginForm.get('passwordVet')?.setValidators([Validators.required]);
    } else if (this.selectedType === '2') {
      this.loginForm.get('usuario')?.setValidators([Validators.required]);
      this.loginForm.get('password')?.setValidators([Validators.required]);
    } else if (this.selectedType === '3') {
      this.loginForm.get('cedula')?.setValidators([Validators.required]);
    }

    // Actualizar el estado de validación
    this.loginForm.updateValueAndValidity();
  }

  // Método para limpiar todas las validaciones
  clearValidations() {
    this.loginForm.get('cedulaVeterinario')?.clearValidators();
    this.loginForm.get('passwordVet')?.clearValidators();
    this.loginForm.get('usuario')?.clearValidators();
    this.loginForm.get('password')?.clearValidators();
    this.loginForm.get('cedula')?.clearValidators();
  }

  login() {
    if (this.selectedType === '1') {
      console.log('Veterinario');
      const credentials = {
        cedula: this.loginForm.value.cedulaVeterinario,
        password: this.loginForm.value.passwordVet
      };
      this.veterinarioService.loginVeterinario(credentials).subscribe({
        next: (data) => {
          this.authService.login(String(data));
          this.router.navigate(['/veterinario/home']);
        },
        error: () => {
          this.error = 'Cédula incorrecta o usuario no encontrado';
        }
      });
    }else if (this.selectedType === '3') {
      console.log('Usuario');
      const credentials = {
        cedula: this.loginForm.value.cedula,
        password: this.loginForm.value.passwordUser
      };
      this.usuarioService.loginUsuario(credentials).subscribe({
        next: (data) => {
          // Redirigir a la página de detalles del usuario
          this.authService.login(String(data));
          this.router.navigate(['/usuario/home']);
        },
        error: (err) => {
          this.error = 'Cédula incorrecta o usuario no encontrado';
        }
      });
    }else if (this.selectedType === '2') {
      const credentials = {
        username: this.loginForm.value.usuario,
        password: this.loginForm.value.password
      };
      console.log('Admin');
      console.log(credentials);
      this.adminService.loginAdmin(credentials).subscribe({
        next: (data) => {
          this.authService.login(String(data));
          this.router.navigate(['/admin/dashboard']);
        },
        error: () => {
          this.error = 'Cédula incorrecta o usuario no encontrado';
        }
      });
    }
    // Similar lógica para los otros tipos de usuario (administrador, cliente)
  }
}
