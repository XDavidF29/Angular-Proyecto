import { Component, OnInit } from '@angular/core';
import { AdminService } from '../servicio/admin.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  public atencionesVeterinarios: any[] = [];
  public estadisticasMascotas: any[] = [];
  public crecimientoUsuarios: any[] = [];
  public tratamientosUltimoMes: number = 0;
  //public tratamientosPorMedicamentoUltimoMes: any[] = [];
  //public veterinariosActivos: number;
  //public veterinariosInactivos: number;
  public mascotasTotales: number = 0;
  //public mascotasActivas: number;
  public gananciasTotales: number = 0;
  public top3Tratamientos: any[] = [];

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.adminService.getDashboardData().subscribe(data => {
      this.atencionesVeterinarios = data.atencionesVeterinarios;
      this.estadisticasMascotas = data.estadisticasMascotas;
      this.crecimientoUsuarios = data.crecimientoUsuarios;
      this.gananciasTotales = data.gananciasTotales;
      this.mascotasTotales = data.mascotasTotales;
      this.top3Tratamientos = data.top3Tratamientos;
      this.tratamientosUltimoMes = data.tratamientosUltimoMes;
      //this.tratamientosPorMedicamentoUltimoMes = data.tratamientosPorMedicamentoUltimoMes;
    });
  }
}
