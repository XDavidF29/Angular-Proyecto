import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'; // Si quieres redirigir a otra página después de aplicar un tratamiento
import { Veterinario } from '../models/Veterinario';
import { VeterinarioServicioService } from '../servicio/veterinario-servicio.service';

@Component({
  selector: 'app-mostrar-todos-veterinarios',
  templateUrl: './mostrar-todos-veterinarios.component.html',
  styleUrls: ['./mostrar-todos-veterinarios.component.css']
})
export class MostrarTodosVeterinariosComponent implements OnInit {
  veterinarios: Veterinario[] = [];
  nombreBusqueda: string = ''; 
  
  constructor(
    private veterinarioService: VeterinarioServicioService,
    private router: Router // Para manejar la redirección si es necesario
  ) {}

  ngOnInit(): void {
    this.cargarVeterinarios();
  }

  cargarVeterinarios(): void {
    this.veterinarioService.findAll().subscribe(
      (data: Veterinario[]) => {
        this.veterinarios = data;
        console.log('Lista de veterinarios cargada:', this.veterinarios);
      },
      error => {
        console.error('Error al obtener los veterinarios:', error);
        alert('Error al cargar la lista de veterinarios');
      }
    );
  }

  deleteVeterinario(id: number): void {
    const confirmar = confirm('¿Estás seguro de que quieres eliminar este veterinario?');
    if (confirmar) {
      this.veterinarioService.delete(id).subscribe(
        () => {
          alert('Veterinario eliminado con éxito');
          this.cargarVeterinarios(); // Refrescar la lista después de eliminar
        },
        error => {
          console.error('Error al eliminar el veterinario:', error);
          alert('Error al eliminar al veterinario');
        }
      );
    }
  }

  openAplicarTratamiento(id: number): void {
    // Aquí puedes agregar la lógica para aplicar el tratamiento o redirigir a otra página
    console.log(`Aplicar tratamiento al veterinario con ID: ${id}`);
    
    // Si quieres redirigir a una página donde se aplique el tratamiento, podrías hacer algo como esto:
    // this.router.navigate(['/tratamiento', id]);
  }

  buscarVeterinarios(): void {
    if (this.nombreBusqueda.trim()) {
      this.veterinarioService.buscarVeterinarios(this.nombreBusqueda).subscribe({
        next: (veterinarios) => {
          this.veterinarios = veterinarios;
        },
        error: (error) => {
          console.error('Error al buscar usuarios', error);
        }
      });
    }
  }
}
