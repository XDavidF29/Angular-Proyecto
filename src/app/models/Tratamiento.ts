import { Mascota } from './Mascota';
import { Veterinario } from './Veterinario';
import { Medicamento } from './Medicamento';

export interface Tratamiento {
    id: number;
    fecha: Date;
    cantidadMascotas: number;
    precio: number;
    mascota: Mascota;  // Relación con Mascota
    veterinario: Veterinario;  // Relación con Veterinario
    medicamentos: Medicamento[];  // Relación con Medicamentos
}
