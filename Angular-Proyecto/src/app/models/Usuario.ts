import { Mascota } from './Mascota';

export interface Usuario {
    id: number;
    nombre: string;
    correo: string;
    celular: number;
    cedula: number;
    mascotas?: Mascota[];  // Relación uno a muchos
}