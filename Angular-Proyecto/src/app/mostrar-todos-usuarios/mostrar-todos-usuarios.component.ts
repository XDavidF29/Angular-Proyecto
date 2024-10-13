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
    // Suscribirse para eliminar la usuario
    this.usuarioService.delete(id).subscribe(
      () => {
        // Refresca la lista de usuarios después de la eliminación
        this.usuarioService.findAll().subscribe(
          (data: Usuario[]) => {
            this.usuarios = data;
          },
          error => {
            console.error('Error al refrescar la lista de usuarios:', error);
            alert('Error al actualizar la lista de usuarios');
          }
        );
        alert('Usuario eliminada con éxito');
      },
      error => {
        console.error('Error al eliminar la usuario:', error);
        alert('Se pudo eliminar al usuario');
      }
    );
  }
}