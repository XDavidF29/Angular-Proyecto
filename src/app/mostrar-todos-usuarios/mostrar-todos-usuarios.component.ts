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
  usuariosFiltrados: Usuario[] = []; // Nueva propiedad para los usuarios filtrados
  nombreBusqueda: string = ''; 
  cantidadMascotas: { [key: number]: number } = {}; // Almacena la cantidad de mascotas por usuario

  constructor(private usuarioService: UsuarioServicioService) {}

  ngOnInit(): void {
    this.usuarioService.findAll().subscribe(
      (data: Usuario[]) => {
        this.usuarios = data;
        this.usuariosFiltrados = data; // Inicialmente, los usuarios filtrados son todos los usuarios
        // Obtener la cantidad de mascotas para cada usuario
        this.usuarios.forEach(usuario => {
          this.obtenerCantidadMascotas(usuario.cedula);
        });
      },
      error => {
        console.error('Error al obtener los usuarios:', error);
        alert('Error al cargar la lista de usuarios');
      }
    );
  }

  obtenerCantidadMascotas(cedula: number): void {
    this.usuarioService.findMascotasByUsuarioId(cedula).subscribe(
      (mascotas) => {
        this.cantidadMascotas[cedula] = mascotas.length; // Guardar la cantidad de mascotas por cédula
      },
      error => {
        console.error('Error al obtener mascotas para el usuario:', error);
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
            this.filtrarUsuarios(); // Filtrar usuarios después de la eliminación
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

  filtrarUsuarios(): void {
    if (this.nombreBusqueda.trim()) {
      this.usuariosFiltrados = this.usuarios.filter(usuario => 
        usuario.nombre.toLowerCase().includes(this.nombreBusqueda.toLowerCase())
      );
    } else {
      this.usuariosFiltrados = this.usuarios; // Si no hay búsqueda, mostrar todos
    }
  }
}
