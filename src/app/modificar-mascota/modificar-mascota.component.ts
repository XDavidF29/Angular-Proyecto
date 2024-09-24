import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router'; // Para obtener el parámetro ID de la URL
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
    private route: ActivatedRoute // Para obtener el ID desde la URL
  ) { }

  ngOnInit(): void {
    // Obtiene el ID de la mascota desde la URL
    const id = Number(this.route.snapshot.paramMap.get('id'));
    
    // Busca la mascota con el ID correspondiente
    this.mascota = this.mascotaService.findById(id);
  }

  modificarMascota(): void {
    if (this.mascota) {
      this.mascotaService.update(this.mascota);
      alert('Mascota modificada con éxito');
    } else {
      alert('Error al modificar la mascota');
    }
  }
}
