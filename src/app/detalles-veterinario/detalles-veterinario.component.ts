import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Veterinario } from '../models/Veterinario'; // Asegúrate de importar el modelo de Veterinario
import { Tratamiento } from '../models/Tratamiento'; // Asegúrate de importar el modelo de Tratamiento
import { VeterinarioServicioService } from '../servicio/veterinario-servicio.service'; // El servicio para obtener los datos del veterinario

@Component({
  selector: 'app-detalles-veterinario',
  templateUrl: './detalles-veterinario.component.html',
  styleUrls: ['./detalles-veterinario.component.css']
})
export class DetallesVeterinarioComponent implements OnInit {
  veterinario: Veterinario | undefined;
  errorMessage: string | null = null;
  tratamientos: Tratamiento[] = []; // Almacena los tratamientos del veterinario
  rolActual: string = '';

  constructor(
    private route: ActivatedRoute,
    private veterinarioService: VeterinarioServicioService
  ) { }

  ngOnInit(): void {
    // Obtener el rol del usuario
    this.rolActual = this.obtenerRolUsuario();
    console.log('Rol actual:', this.rolActual);

    if (this.rolActual === 'Admin') {
      // Si el rol es 'Admin', utilizar el método findById()
      const idParam = this.route.snapshot.paramMap.get('id');
      if (idParam) {
        const id = Number(idParam);
        if (!isNaN(id)) {
          this.veterinarioService.findById(id).subscribe({
            next: (data: Veterinario) => {
              this.veterinario = data;
              // Cargar los tratamientos del veterinario
              this.loadVeterinarioTratamientos(this.veterinario.id);
            },
            error: (error) => {
              console.error('Error al obtener los detalles del veterinario:', error);
              this.errorMessage = 'Error al obtener los detalles del veterinario';
            }
          });
        } else {
          this.errorMessage = 'ID de veterinario no válido';
        }
      } else {
        this.errorMessage = 'No se encontró el ID del veterinario en la URL';
      }
    } else if (this.rolActual === 'Veterinario') {
      // Si el rol es 'Veterinario', utilizar el método veterinarioHome()
      this.veterinarioService.veterinarioHome().subscribe({
        next: (data) => {
          console.log('Datos recibidos:', data); // Depuración
          this.veterinario = data;

          // Cargar los tratamientos del veterinario
          this.loadVeterinarioTratamientos(this.veterinario.id);
        },
        error: (err) => {
          console.error('Error al obtener los detalles del veterinario:', err);
          this.errorMessage = 'Error al obtener los detalles del veterinario';
        }
      });
    } else {
      this.errorMessage = 'Rol de usuario no reconocido';
    }
  }

  // Método para obtener el rol del usuario desde el almacenamiento local
  private obtenerRolUsuario(): string {
    return localStorage.getItem('role') || '';
  }

  // Método separado para cargar los tratamientos del veterinario
  private loadVeterinarioTratamientos(veterinarioId: number) {
    this.veterinarioService.findTratamientosByVeterinarioId(veterinarioId).subscribe({
      next: (tratamientos: Tratamiento[]) => {
        this.tratamientos = tratamientos;
        console.log(this.tratamientos); // Verifica los tratamientos cargados
      },
      error: (error) => {
        console.error('Error al obtener los tratamientos del veterinario:', error);
        this.errorMessage = 'Error al obtener los tratamientos del veterinario';
      }
    });
  }

  // Método para manejar el error al cargar la imagen
  onImageError(veterinario: Veterinario): void {
    veterinario.foto = 'assets/images/error.png'; // Quitar la URL de la imagen para mostrar el mensaje de error
  }
}
