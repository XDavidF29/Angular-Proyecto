import { Tratamiento } from './Tratamiento';

export interface Medicamento {
    id: number;
    nombre: string;
    precioCompra: number;
    precioVenta: number;
    unidadesVendidas: number;
    unidadesDisponibles: number;
    tratamientos: Tratamiento[];
}
