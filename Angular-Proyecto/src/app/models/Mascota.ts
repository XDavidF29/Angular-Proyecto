export interface Mascota {
    id: number;
    nombre: string;
    raza: string;
    edad: number;
    peso: number;
    foto: string;
    enfermedad: string;
    estado: string;
    usuario: { cedula: number };
}
