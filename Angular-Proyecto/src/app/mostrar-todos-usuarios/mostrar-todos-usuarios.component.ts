import { Component } from '@angular/core';
import { Usuario } from '../models/Usuario';
import { UsuarioServicioService } from '../servicio/usuario-servicio.service';

@Component({
  selector: 'app-mostrar-todos-usuarios',
  templateUrl: './mostrar-todos-usuarios.component.html',
  styleUrls: ['./mostrar-todos-usuarios.component.css']
})
export class MostrarTodosUsuariosComponent {
  usuarios: Usuario[] = [];
  nombreBusqueda: string = ''; 

  constructor(private usuarioService: UsuarioServicioService){
  
  }

  ngOnInit(): void {
    this.usuarioService.findAll().subscribe(
      (data: Usuario[]) => {
        this.usuarios = data;
      },
      error => {
        console.error('Error al obtener las usuarios:', error);
        alert('Error al cargar la lista de usuarios');
      }
    );
  }
  

  deleteUsuario(id: number) {
    this.usuarioService.delete(id).subscribe(
      () => {
        // Refrescar la lista de usuarios después de la eliminación
        this.usuarioService.findAll().subscribe(
          (data: Usuario[]) => {
            this.usuarios = data;  // Verifica si data está siendo retornado correctamente
            console.log('Usuarios después de la eliminación:', this.usuarios);
          },
          error => {
            console.error('Error al refrescar la lista de usuarios:', error);
            alert('Error al actualizar la lista de usuarios');
          }
        );
        alert('Usuario eliminado con éxito');
      },
      error => {
        console.error('Error al eliminar el usuario:', error);
        alert('Error al eliminar al usuario');
      }
    );
  }

  buscarUsuarios(): void {
    if (this.nombreBusqueda.trim()) {
      this.usuarioService.buscarUsuarios(this.nombreBusqueda).subscribe({
        next: (usuarios) => {
          this.usuarios = usuarios;
        },
        error: (error) => {
          console.error('Error al buscar usuarios', error);
        }
      });
    }
  }
}