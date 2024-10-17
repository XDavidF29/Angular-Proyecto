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

      // Llama al servicio para crear el tratamiento
      this.mascotaServicio.addTratamiento(this.mascota.id, nuevoTratamiento, this.veterinarioSeleccionado.cedula).subscribe({
        next: (response) => {
          console.log('Tratamiento asignado exitosamente', response);
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
    const nuevoMedicamento: Medicamento = {
      id: 0,  // Establecer un ID adecuado si es necesario
      nombre: '',
      precioCompra: 0,
      precioVenta: 0,
      unidadesVendidas: 0,
      unidadesDisponibles: 0,
      tratamientos: []  // Puedes dejarlo vacío o ajustarlo según necesites
    };

    this.medicamentosSeleccionados.push(nuevoMedicamento);
  }

  // Función para eliminar un medicamento de la lista de medicamentos seleccionados
  eliminarMedicamento(index: number): void {
    if (index > -1) {
      this.medicamentosSeleccionados.splice(index, 1);
    }
  }
}
