import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Importa tus componentes
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { CrearMascotaComponent } from './crear-mascota/crear-mascota.component';
import { CrearUsuarioComponent } from './crear-usuario/crear-usuario.component';
import { CrearVeterinarioComponent } from './crear-veterinario/crear-veterinario.component';
import { DetallesMascotaComponent } from './detalles-mascota/detalles-mascota.component';
import { DetallesUsuarioComponent } from './detalles-usuario/detalles-usuario.component';
import { DetallesVeterinarioComponent } from './detalles-veterinario/detalles-veterinario.component';
import { ErrorComponent } from './error/error.component';
import { FindComponent } from './find/find.component';
import { IndexComponent } from './index/index.component';
import { InstalacionesComponent } from './instalaciones/instalaciones.component';
import { LoginUsuarioComponent } from './login-usuario/login-usuario.component';
import { LoginVeterinarioComponent } from './login-veterinario/login-veterinario.component';
import { ModificarMascotaComponent } from './modificar-mascota/modificar-mascota.component';
import { ModificarUsuarioComponent } from './modificar-usuario/modificar-usuario.component';
import { ModificarVeterinarioComponent } from './modificar-veterinario/modificar-veterinario.component';
import { MostrarTodasMascotasComponent } from './mostrar-todas-mascotas/mostrar-todas-mascotas.component';
import { MostrarTodosUsuariosComponent } from './mostrar-todos-usuarios/mostrar-todos-usuarios.component';
import { MostrarTodosVeterinariosComponent } from './mostrar-todos-veterinarios/mostrar-todos-veterinarios.component';
import { PromocionesComponent } from './promociones/promociones.component';
import { ServiciosComponent } from './servicios/servicios.component';
import { UsuarioExitosoComponent } from './usuario-exitoso/usuario-exitoso.component';
import { AsignarTratamientoComponent } from './asignar-tratamiento/asignar-tratamiento.component';

const routes: Routes = [
  { path: 'admin/dashboard', component: AdminDashboardComponent },
  { path: 'admin/mascotas/estadisticas', component: AdminDashboardComponent },
  { path: 'mascota/tratamiento/:id', component: AsignarTratamientoComponent},
  { path: 'mascota/add', component: CrearMascotaComponent },
  { path: 'usuario/add', component: CrearUsuarioComponent },
  { path: 'usuario/find/:id', component: DetallesUsuarioComponent },
  { path: 'mascota/find/:id', component: DetallesMascotaComponent },
  { path: 'error', component: ErrorComponent },
  { path: 'veterinario/find/:id', component: DetallesVeterinarioComponent },
  { path: 'veterinario/add', component: CrearVeterinarioComponent },
  { path: 'find', component: FindComponent },
  { path: 'index', component: IndexComponent },
  { path: 'instalaciones', component: InstalacionesComponent },
  { path: 'login-usuario', component: LoginUsuarioComponent },
  { path: 'login-veterinario', component: LoginVeterinarioComponent },
  { path: 'mascota/update/:id', component: ModificarMascotaComponent },
  { path: 'usuario/update/:id', component: ModificarUsuarioComponent },
  {path: 'veterinario/update/:id', component: ModificarVeterinarioComponent},
  { path: 'mascota/all', component: MostrarTodasMascotasComponent },
  { path: 'veterinario/all', component: MostrarTodosVeterinariosComponent },
  { path: 'usuario/all', component: MostrarTodosUsuariosComponent },
  { path: 'promociones', component: PromocionesComponent },
  { path: 'servicios', component: ServiciosComponent },
  { path: 'usuario-exitoso', component: UsuarioExitosoComponent },
  { path: '', redirectTo: '/index', pathMatch: 'full' },  
  { path: '**', component: ErrorComponent }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

}
export { routes };

