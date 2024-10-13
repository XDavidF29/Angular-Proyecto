import { Tratamiento } from './Tratamiento';

export interface Veterinario {
    id: number;
    cedula: string;
    password: string;
    especialidad: string;
    atenciones: number;
    nombre: string;
    foto: string;
    tratamientos: Tratamiento[];  // Relación uno a muchos con Tratamiento
}
