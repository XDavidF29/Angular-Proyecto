import { Tratamiento } from './Tratamiento';

export interface Medicamento {
    id: number;
    nombre: string;
    precio_compra: number;
    precio_venta: number;
    unidadesVendidas: number;
    unidadesDisponibles: number;
    tratamientos: Tratamiento[];
}
