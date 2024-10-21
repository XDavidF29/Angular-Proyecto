import { Component } from '@angular/core';
import { TratamientoService } from '../servicio/tratamiento.service';
import { MascotaServicioService } from '../servicio/mascota-servicio.service';
import { Tratamiento } from '../models/Tratamiento';
import { Mascota } from '../models/Mascota';

@Component({
  selector: 'app-aplicar-tratamiento',
  templateUrl: './aplicar-tratamiento.component.html',
  styleUrls: ['./aplicar-tratamiento.component.css']
})
export class AplicarTratamientoComponent {
  mascotaId: number = 0;
  tratamiento: Tratamiento = {} as Tratamiento;
  cedulaVeterinario: string= '';

  constructor(private tratamientoService: MascotaServicioService) {}

  aplicarTratamiento() {
    this.tratamientoService.addTratamiento(this.mascotaId, this.tratamiento, this.cedulaVeterinario)
      .subscribe({
        next: (mascota: Mascota) => {
          console.log('Tratamiento agregado correctamente', mascota);
          // Manejo adicional de la respuesta
        },
        error: (error: any) => {
          console.error('Error al agregar tratamiento', error);
        }
      });
  }
  
}
