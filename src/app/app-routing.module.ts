import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Importa tus componentes
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { CrearMascotaComponent } from './crear-mascota/crear-mascota.component';
import { CrearUsuarioComponent } from './crear-usuario/crear-usuario.component';
import { DetallesMascotaComponent } from './detalles-mascota/detalles-mascota.component';
import { DetallesUsuarioComponent } from './detalles-usuario/detalles-usuario.component';
import { ErrorComponent } from './error/error.component';
import { FindComponent } from './find/find.component';
import { IndexComponent } from './index/index.component';
import { InstalacionesComponent } from './instalaciones/instalaciones.component';
import { LoginUsuarioComponent } from './login-usuario/login-usuario.component';
import { ModificarMascotaComponent } from './modificar-mascota/modificar-mascota.component';
import { ModificarUsuarioComponent } from './modificar-usuario/modificar-usuario.component';
import { MostrarTodasMascotasComponent } from './mostrar-todas-mascotas/mostrar-todas-mascotas.component';
import { MostrarTodosUsuariosComponent } from './mostrar-todos-usuarios/mostrar-todos-usuarios.component';
import { PromocionesComponent } from './promociones/promociones.component';
import { ServiciosComponent } from './servicios/servicios.component';
import { UsuarioExitosoComponent } from './usuario-exitoso/usuario-exitoso.component';

const routes: Routes = [
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { path: 'crear-mascota', component: CrearMascotaComponent },
  { path: 'crear-usuario', component: CrearUsuarioComponent },
  { path: 'detalles-usuario', component: DetallesUsuarioComponent },
  { path: 'detalles-mascota/:id', component: DetallesMascotaComponent },
  { path: 'error', component: ErrorComponent },
  { path: 'find', component: FindComponent },
  { path: 'index', component: IndexComponent },
  { path: 'instalaciones', component: InstalacionesComponent },
  { path: 'login-usuario', component: LoginUsuarioComponent },
  { path: 'modificar-mascota/:id', component: ModificarMascotaComponent },
  { path: 'modificar-usuario', component: ModificarUsuarioComponent },
  { path: 'mostrar-todas-mascotas', component: MostrarTodasMascotasComponent },
  { path: 'mostrar-todos-usuarios', component: MostrarTodosUsuariosComponent },
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

