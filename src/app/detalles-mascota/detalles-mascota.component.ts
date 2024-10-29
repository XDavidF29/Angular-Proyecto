import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Mascota } from '../models/Mascota';
import { MascotaServicioService } from '../servicio/mascota-servicio.service';
import { Tratamiento } from '../models/Tratamiento';

@Component({
  selector: 'app-detalles-mascota',
  templateUrl: './detalles-mascota.component.html',
  styleUrls: ['./detalles-mascota.component.css']
})
export class DetallesMascotaComponent{
  mascota: Mascota | undefined;
  errorMessage: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private mascotaService: MascotaServicioService
  ) { }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      const id = Number(idParam);
      if (!isNaN(id)) {
        this.mascotaService.findById(id).subscribe(
          (data: Mascota) => {
            this.mascota = data;

            this.mascotaService.buscarTratamientoPorMascota(this.mascota.id).subscribe(
              (tratamientos: Tratamiento[]) => {
                if (this.mascota) {
                  this.mascota.tratamientos = tratamientos;
                  console.log(tratamientos);
                }
              },
              error => {
                console.error('Error fetching tratamientos:', error);
                this.errorMessage = 'Error al obtener los tratamientos de la mascota';
              }
            );
          },
          error => {
            console.error('Error fetching mascota:', error);
            this.errorMessage = 'Error al obtener los detalles de la mascota';
          }
        );
      } else {
        this.errorMessage = 'ID de mascota no válido';
      }
    } else {
      this.errorMessage = 'No se encontró el ID de la mascota en la URL';
    }
  }
}
