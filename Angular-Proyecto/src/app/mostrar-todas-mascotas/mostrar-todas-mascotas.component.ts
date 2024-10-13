import { Component, OnInit } from '@angular/core';
import { Mascota } from '../models/Mascota';
import { MascotaServicioService } from '../servicio/mascota-servicio.service';

@Component({
  selector: 'app-mostrar-todas-mascotas',
  templateUrl: './mostrar-todas-mascotas.component.html',
  styleUrls: ['./mostrar-todas-mascotas.component.css']
})
export class MostrarTodasMascotasComponent implements OnInit {

  mascotas: Mascota[] = [];

  constructor(private mascotaServicio: MascotaServicioService) { }

  ngOnInit(): void {
    this.mascotaServicio.findAll().subscribe(
      (data: Mascota[]) => {
        this.mascotas = data;
      },
      error => {
        console.error('Error al obtener las mascotas:', error);
        alert('Error al cargar la lista de mascotas');
      }
    );
  }

  deleteMascota(id: number) {
    if (confirm('¿Estás seguro de que deseas eliminar esta mascota?')) {
      this.mascotaServicio.delete(id).subscribe(
        () => {
          // Filtra las mascotas eliminadas para actualizar la lista local sin otra llamada al servidor
          this.mascotas = this.mascotas.filter(mascota => mascota.id !== id);
          alert('Mascota eliminada con éxito');
        },
        error => {
          console.error('Error al eliminar la mascota:', error);
          alert('Error al eliminar la mascota');
        }
      );
    }
  }
}
