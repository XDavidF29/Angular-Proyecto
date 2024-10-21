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

  constructor(
    private route: ActivatedRoute,
    private veterinarioService: VeterinarioServicioService
  ) { }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      const id = Number(idParam);
      if (!isNaN(id)) {
        this.veterinarioService.findById(id).subscribe(
          (data: Veterinario) => {
            this.veterinario = data;
            // Cargar los tratamientos del veterinario
            this.veterinarioService.findTratamientosByVeterinarioId(this.veterinario.id).subscribe(
              (tratamientos: Tratamiento[]) => {
                this.tratamientos = tratamientos; // Asigna los tratamientos cargados
                console.log(this.tratamientos); // Verifica los tratamientos
              },
              (error: any) => {
                console.error('Error al obtener los tratamientos del veterinario:', error);
                this.errorMessage = 'Error al obtener los tratamientos del veterinario';
              }
            );
          },
          (error) => {
            console.error('Error al obtener los detalles del veterinario:', error);
            this.errorMessage = 'Error al obtener los detalles del veterinario';
          }
        );
      } else {
        this.errorMessage = 'ID de veterinario no válido';
      }
    } else {
      this.errorMessage = 'No se encontró el ID del veterinario en la URL';
    }
  }
}
