import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Mascota } from '../models/Mascota';
import { Usuario } from '../models/Usuario';
import { UsuarioServicioService } from '../servicio/usuario-servicio.service';

@Component({
  selector: 'app-detalles-usuario',
  templateUrl: './detalles-usuario.component.html',
  styleUrls: ['./detalles-usuario.component.css']
})
export class DetallesUsuarioComponent implements OnInit {
  usuario: Usuario | undefined;
  errorMessage: string | null = null;
  mascotas: Mascota[] = [];
  rolActual: string = '';

  constructor(
    private route: ActivatedRoute,
    private usuarioService: UsuarioServicioService
  ) {}

  ngOnInit(): void {
    const rolActual = this.obtenerRolUsuario();
    console.log('Rol actual:', rolActual);

    if (rolActual === 'Usuario') {
      // Si el rol es 'usuario', utilizar el método usuarioHome()
      this.usuarioService.usuarioHome().subscribe({
        next: (data) => {
          this.usuario = data;
          console.log(this.usuario);

          // Cargar las mascotas del usuario utilizando el ID recuperado
          if (this.usuario?.cedula) {
            this.loadUsuarioMascotas(this.usuario.cedula);
          }
        },
        error: (error) => {
          console.error('Error fetching user details:', error);
          this.errorMessage = 'Error al obtener los detalles del usuario';
        }
      });
    } else {
      // Si el rol no es 'usuario', utiliza el método findById()
      const idParam = this.route.snapshot.paramMap.get('id');
      if (idParam) {
        const id = Number(idParam);
        if (!isNaN(id)) {
          this.usuarioService.findById(id).subscribe({
            next: (data: Usuario) => {
              this.usuario = data;

              // Cargar las mascotas del usuario
              if (this.usuario?.cedula) {
                this.loadUsuarioMascotas(this.usuario.cedula);
              }
            },
            error: (error) => {
              console.error('Error fetching user details:', error);
              this.errorMessage = 'Error al obtener los detalles del usuario';
            }
          });
        } else {
          this.errorMessage = 'ID de usuario no válido';
        }
      } else {
        this.errorMessage = 'No se encontró el ID del usuario en la URL';
      }
    }
  }

  private obtenerRolUsuario(): string {
    // Recupera el rol del usuario desde localStorage o cualquier otro método de almacenamiento
    return localStorage.getItem('role') || '';
  }

  private loadUsuarioMascotas(cedula: number) {
    this.usuarioService.findMascotasByUsuarioId(cedula).subscribe({
      next: (mascotas: Mascota[]) => {
        this.mascotas = mascotas;
        console.log(this.mascotas);
      },
      error: (error) => {
        console.error('Error fetching user mascotas:', error);
        this.errorMessage = 'Error al obtener las mascotas del usuario';
      }
    });
  }
}
