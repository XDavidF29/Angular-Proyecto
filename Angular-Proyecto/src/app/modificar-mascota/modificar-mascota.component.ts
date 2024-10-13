import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Mascota } from '../models/Mascota';
import { MascotaServicioService } from '../servicio/mascota-servicio.service';

@Component({
  selector: 'app-modificar-mascota',
  templateUrl: './modificar-mascota.component.html',
  styleUrls: ['./modificar-mascota.component.css']
})
export class ModificarMascotaComponent implements OnInit {

  mascota: Mascota | undefined;

  constructor(
    private mascotaService: MascotaServicioService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    // Obtiene el ID de la mascota desde la URL
    const id = Number(this.route.snapshot.paramMap.get('id'));
    
    // Busca la mascota con el ID correspondiente y suscríbete para obtener los datos
    this.mascotaService.findById(id).subscribe(
      (data: Mascota) => {
        this.mascota = data;
      },
      error => {
        console.error('Error al buscar la mascota:', error);
      }
    );
  }

  modificarMascota(): void {
    if (this.mascota) {
      // Llama al servicio de actualización y suscríbete para manejar el resultado
      this.mascotaService.update(this.mascota).subscribe(
        () => {
          alert('Mascota modificada con éxito');
        },
        error => {
          console.error('Error al modificar la mascota:', error);
          alert('Error al modificar la mascota');
        }
      );
    } else {
      alert('No se pudo modificar la mascota porque no fue encontrada');
    }
  }
  
}
