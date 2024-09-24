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
    this.mascotas = this.mascotaServicio.findAll();
  }

  deleteMascota(id: number): void {
    this.mascotaServicio.delete(id);
    this.mascotas = this.mascotaServicio.findAll();  // Refresca la lista después de eliminar
  }
}
