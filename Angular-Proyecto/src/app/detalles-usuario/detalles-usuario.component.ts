import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Mascota } from '../models/Mascota'; // Asegúrate de importar el modelo de Mascota
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
  mascotas: Mascota[] = []; // Agrega una propiedad para almacenar las mascotas

  constructor(
    private route: ActivatedRoute,
    private usuarioService: UsuarioServicioService
  ) { }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      const id = Number(idParam);
      if (!isNaN(id)) {
        this.usuarioService.findById(id).subscribe(
          (data: Usuario) => {
            this.usuario = data;
            // Cargar las mascotas del usuario
            this.usuarioService.findMascotasByUsuarioId(this.usuario.cedula).subscribe(
              (mascotas: Mascota[]) => {
                this.mascotas = mascotas; // Asigna las mascotas cargadas
                console.log(this.mascotas); // Verifica las mascotas
              },
              (error: any) => {
                console.error('Error fetching user mascotas:', error);
                this.errorMessage = 'Error al obtener las mascotas del usuario';
              }
            );
          },
          (error) => {
            console.error('Error fetching user details:', error);
            this.errorMessage = 'Error al obtener los detalles del usuario';
          }
        );
      } else {
        this.errorMessage = 'ID de usuario no válido';
      }
    } else {
      this.errorMessage = 'No se encontró el ID del usuario en la URL';
    }
  }
}
