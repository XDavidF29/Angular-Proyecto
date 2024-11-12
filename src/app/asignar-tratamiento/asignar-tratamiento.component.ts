import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MedicamentoService } from '../servicio/medicamento.service';
import { MascotaServicioService } from '../servicio/mascota-servicio.service';
import { VeterinarioServicioService } from '../servicio/veterinario-servicio.service';
import { Mascota } from '../models/Mascota';
import { Medicamento } from '../models/Medicamento';
import { Veterinario } from '../models/Veterinario';
import { Tratamiento } from '../models/Tratamiento';

@Component({
  selector: 'app-asignar-tratamiento',
  templateUrl: './asignar-tratamiento.component.html',
  styleUrls: ['./asignar-tratamiento.component.css']
})
export class AsignarTratamientoComponent implements OnInit {

  mascota: Mascota | undefined;
  medicamentosDisponibles: Medicamento[] = [];
  medicamentosSeleccionados: Medicamento[] = [];  // Lista de medicamentos seleccionados
  veterinariosDisponibles: Veterinario[] = [];
  veterinarioSeleccionado: Veterinario | undefined;
  mensajeError: string = '';

  // Nuevas propiedades para el tratamiento
  tratamiento = {
    fecha: new Date(), // Inicializar con la fecha actual
    precio: 0,         // Inicializar el precio a 0
  };

  medicamentoSeleccionado: Medicamento | undefined; // Medicamento que se está agregando

  constructor(
    private medicamentoService: MedicamentoService,
    private mascotaServicio: MascotaServicioService,
    private veterinarioService: VeterinarioServicioService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Obtener el id de la ruta
    const mascotaId = this.route.snapshot.paramMap.get('id');

    if (mascotaId) {
      // Cargar la mascota por el ID
      this.mascotaServicio.findById(+mascotaId).subscribe({
        next: (mascota: Mascota) => {
          this.mascota = mascota;
          console.log('Mascota cargada:', this.mascota);
        },
        error: (error) => {
          console.error('Error al cargar la mascota', error);
        }
      });
    }

    // Cargar todos los medicamentos disponibles
    this.medicamentoService.findAll().subscribe({
      next: (medicamentos: Medicamento[]) => {
        this.medicamentosDisponibles = medicamentos;
        console.log('Medicamentos disponibles cargados:', this.medicamentosDisponibles);
      },
      error: (error) => {
        console.error('Error al cargar los medicamentos disponibles', error);
      }
    });

    // Cargar todos los veterinarios disponibles
    this.veterinarioService.findAll().subscribe({
      next: (veterinarios: Veterinario[]) => {
        this.veterinariosDisponibles = veterinarios;
        console.log('Veterinarios disponibles cargados:', this.veterinariosDisponibles);
      },
      error: (error) => {
        console.error('Error al cargar los veterinarios disponibles', error);
      }
    });

     // Inicializar la fecha con la fecha actual en formato YYYY-MM-DD
     const today = new Date();
     const dd: string | number = today.getDate();
     const mm: string | number = today.getMonth() + 1; // Enero es 0
     const yyyy: number = today.getFullYear();
 
     this.tratamiento.fecha = new Date(yyyy, mm - 1, dd);
  }

  // Función para agregar un tratamiento a la mascota actual con un veterinario y medicamentos
  agregarTratamiento(): void {
    this.mensajeError = '';
    if (this.mascota && this.veterinarioSeleccionado && this.medicamentosSeleccionados.length > 0) {
        const nuevoTratamiento: Tratamiento = {
            fecha: this.tratamiento.fecha,
            precio: this.tratamiento.precio,
            mascota: this.mascota,
            veterinario: this.veterinarioSeleccionado,
            medicamentos: this.medicamentosSeleccionados
        };

        // Llama al servicio para crear el tratamiento
        this.mascotaServicio.addTratamiento(this.mascota.id, nuevoTratamiento, this.veterinarioSeleccionado.cedula).subscribe({
            next: (response) => {
                this.router.navigate(['/mascota/find/', response.id]);
            },
            error: (error) => {
                if (error.status === 400) {
                    this.mensajeError = error.error; 
                } else {
                    console.error('Error al asignar tratamiento', error);
                }
            }
        });
    } else {
        console.error('Debe seleccionar un veterinario y al menos un medicamento');
    }
}


  // Función para agregar un medicamento a la lista de medicamentos seleccionados
  agregarMedicamento(): void {
    if (this.medicamentoSeleccionado) {
      this.medicamentosSeleccionados.push(this.medicamentoSeleccionado);
      this.tratamiento.precio += this.medicamentoSeleccionado.precio_venta;
      
      // Eliminar el medicamento seleccionado de la lista de disponibles
      if (this.medicamentoSeleccionado) {
        this.medicamentosDisponibles = this.medicamentosDisponibles.filter(medicamento => medicamento.id !== this.medicamentoSeleccionado!.id);
      }
      
      this.medicamentoSeleccionado = undefined; // Limpiar la selección
    } else {
      console.error('Debe seleccionar un medicamento');
    }
  }

  // Función para eliminar un medicamento de la lista de medicamentos seleccionados
  eliminarMedicamento(index: number): void {
    if (index > -1) {
      const medicamentoEliminado = this.medicamentosSeleccionados.splice(index, 1)[0];

      this.tratamiento.precio -= medicamentoEliminado.precio_venta;
      
      // Agregar el medicamento de nuevo a la lista de disponibles
      this.medicamentosDisponibles.push(medicamentoEliminado);
      
    }
  }
}