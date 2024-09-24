import { Injectable } from '@angular/core';
import { Mascota } from '../models/Mascota';

@Injectable({
  providedIn: 'root'
})
export class MascotaServicioService {
  constructor() { }
  mascotas: Mascota[] = [
    {
      id: 1,
      nombre: 'Bobby',
      raza: 'Labrador',
      edad: 5,
      peso: 25.4,
      foto: "../assets/images/labrador.png",
      enfermedad: 'Ninguna',
      estado: 'Saludable',
      usuarioId: 101
    },
    {
      id: 2,
      nombre: 'Mimi',
      raza: 'Siames',
      edad: 3,
      peso: 4.8,
      foto: "../assets/images/labrador.png",
      enfermedad: 'Asma',
      estado: 'Estable',
      usuarioId: 102
    },
    {
      id: 3,
      nombre: 'Rocky',
      raza: 'Bulldog',
      edad: 6,
      peso: 28.7,
      foto: "../assets/images/labrador.png",
      enfermedad: 'Dermatitis',
      estado: 'En tratamiento',
      usuarioId: 103
    },
    {
      id: 4,
      nombre: 'Luna',
      raza: 'Golden Retriever',
      edad: 2,
      peso: 22.1,
      foto: "../assets/images/labrador.png",
      enfermedad: 'Ninguna',
      estado: 'Saludable',
      usuarioId: 104
    },
    {
      id: 5,
      nombre: 'Tommy',
      raza: 'Beagle',
      edad: 4,
      peso: 12.5,
      foto: "../assets/images/labrador.png",
      enfermedad: 'Otitis',
      estado: 'Estable',
      usuarioId: 105
    },
    {
      id: 6,
      nombre: 'Felix',
      raza: 'Persa',
      edad: 5,
      peso: 5.6,
      foto: "../assets/images/labrador.png",
      enfermedad: 'Ninguna',
      estado: 'Saludable',
      usuarioId: 106
    },
    {
      id: 7,
      nombre: 'Max',
      raza: 'Pastor Alemán',
      edad: 7,
      peso: 32.4,
      foto: "../assets/images/labrador.png",
      enfermedad: 'Artritis',
      estado: 'En tratamiento',
      usuarioId: 107
    },
    {
      id: 8,
      nombre: 'Bella',
      raza: 'Shih Tzu',
      edad: 3,
      peso: 6.2,
      foto: "../assets/images/labrador.png",
      enfermedad: 'Ninguna',
      estado: 'Saludable',
      usuarioId: 108
    },
    {
      id: 9,
      nombre: 'Simba',
      raza: 'Maine Coon',
      edad: 2,
      peso: 9.3,
      foto: "../assets/images/labrador.png",
      enfermedad: 'Problemas digestivos',
      estado: 'Mejorando',
      usuarioId: 109
    },
    {
      id: 10,
      nombre: 'Coco',
      raza: 'Chihuahua',
      edad: 1,
      peso: 3.5,
      foto: "../assets/images/labrador.png",
      enfermedad: 'Ninguna',
      estado: 'Saludable',
      usuarioId: 110
    }
  ];

  // Retorna todas las mascotas
  findAll(): Mascota[] {
    return this.mascotas;
  }

  // Busca una mascota por su id
  findById(id: number): Mascota {
    return this.mascotas.find(mascota => mascota.id === id)!;
  }

  // Actualiza una mascota existente
  update(mascota: Mascota): void {
    const index = this.mascotas.findIndex(m => m.id === mascota.id);
    if (index !== -1) {
      this.mascotas[index] = mascota;
    }
  }

  // Elimina una mascota por su id
  delete(id: number): void {
    this.mascotas = this.mascotas.filter(m => m.id !== id);
  } 
  addMascota(nuevaMascota: Mascota): void {
    nuevaMascota.id = this.mascotas.length + 1; // Generar un nuevo ID
    this.mascotas.push(nuevaMascota); // Añadir la nueva mascota
  }
}
