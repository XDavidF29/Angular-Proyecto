import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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
    private route: ActivatedRoute
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
    if (this.mascota && this.veterinarioSeleccionado && this.medicamentosSeleccionados.length > 0) {
      const nuevoTratamiento: Tratamiento = {
        id: 1, // Ajustar según el ID real o manejarlo desde el backend
        fecha: this.tratamiento.fecha,
        precio: this.tratamiento.precio,
        mascota: this.mascota,
        veterinario: this.veterinarioSeleccionado,
        medicamentos: this.medicamentosSeleccionados
      };
      for (const medic of this.medicamentosSeleccionados) {
        console.log('Medicamento antes de actualizar:', medic);
      
        if (medic.unidadesDisponibles > 0) {
          medic.unidadesDisponibles -= 1;  // Restar una unidad disponible
          medic.unidadesVendidas += 1;     // Aumentar una unidad vendida
      
          // Actualizar el medicamento en el backend
          this.medicamentoService.update(medic).subscribe(
            (response) => console.log('Medicamento actualizado en el servidor:', response),
            (error) => console.error('Error al actualizar el medicamento', error)
          );
        } else {
          console.error(`No hay suficientes unidades disponibles para el medicamento: ${medic.nombre}`);
        }
      }
      
      

      // Llama al servicio para crear el tratamiento
      this.mascotaServicio.addTratamiento(this.mascota.id, nuevoTratamiento, this.veterinarioSeleccionado.cedula).subscribe({
        next: (response) => {
          console.log('Tratamiento asignado exitosamente', response);
          // Aquí podrías resetear la lista de medicamentos seleccionados después de agregar el tratamiento
          this.medicamentosSeleccionados = []; // Limpiar la lista de medicamentos seleccionados
        },
        error: (error) => {
          console.error('Error al asignar tratamiento', error);
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
      
      // Agregar el medicamento de nuevo a la lista de disponibles
      this.medicamentosDisponibles.push(medicamentoEliminado);
    }
  }
}
