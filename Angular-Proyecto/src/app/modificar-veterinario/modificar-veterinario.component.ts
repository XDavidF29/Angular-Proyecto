import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Veterinario } from '../models/Veterinario'; // Asegúrate de importar el modelo de Veterinario
import { VeterinarioServicioService } from '../servicio/veterinario-servicio.service'; // Asegúrate de importar el servicio

@Component({
  selector: 'app-modificar-veterinario',
  templateUrl: './modificar-veterinario.component.html',
  styleUrls: ['./modificar-veterinario.component.css']
})
export class ModificarVeterinarioComponent implements OnInit {
  
  veterinario: Veterinario | undefined;

  constructor(
    private veterinarioService: VeterinarioServicioService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    // Obtiene el ID del veterinario desde la URL
    const id = Number(this.route.snapshot.paramMap.get('id'));
    
    // Busca el veterinario con el ID correspondiente y suscríbete para obtener los datos
    this.veterinarioService.findById(id).subscribe(
      (data: Veterinario) => {
        this.veterinario = data;
      },
      error => {
        console.error('Error al buscar el veterinario:', error);
      }
    );
  }

  modificarVeterinario(): void {
    if (this.veterinario) {
      // Llama al servicio de actualización y suscríbete para manejar el resultado
      this.veterinarioService.update(this.veterinario).subscribe(
        () => {
          alert('Veterinario modificado con éxito');
        },
        error => {
          console.error('Error al modificar el veterinario:', error);
          alert('Error al modificar el veterinario');
        }
      );
    } else {
      alert('No se pudo modificar el veterinario porque no fue encontrado');
    }
  }
}
