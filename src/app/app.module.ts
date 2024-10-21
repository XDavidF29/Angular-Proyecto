import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AppComponent } from './app.component';
import { CrearMascotaComponent } from './crear-mascota/crear-mascota.component';
import { CrearUsuarioComponent } from './crear-usuario/crear-usuario.component';
import { DetallesMascotaComponent } from './detalles-mascota/detalles-mascota.component';
import { DetallesUsuarioComponent } from './detalles-usuario/detalles-usuario.component';
import { ErrorComponent } from './error/error.component';
import { FindComponent } from './find/find.component';
import { FooterComponent } from "./footer/footer.component";
import { HeaderComponent } from "./header/header.component";
import { InstalacionesComponent } from './instalaciones/instalaciones.component';
import { LoginUsuarioComponent } from './login-usuario/login-usuario.component';
import { ModificarMascotaComponent } from './modificar-mascota/modificar-mascota.component';
import { ModificarUsuarioComponent } from './modificar-usuario/modificar-usuario.component';
import { MostrarTodasMascotasComponent } from './mostrar-todas-mascotas/mostrar-todas-mascotas.component';
import { MostrarTodosUsuariosComponent } from './mostrar-todos-usuarios/mostrar-todos-usuarios.component';
import { MostrarTodosVeterinariosComponent } from './mostrar-todos-veterinarios/mostrar-todos-veterinarios.component';
import { PromocionesComponent } from './promociones/promociones.component';
import { ServiciosComponent } from './servicios/servicios.component';
import { UsuarioExitosoComponent } from './usuario-exitoso/usuario-exitoso.component';
import { AplicarTratamientoComponent } from './aplicar-tratamiento/aplicar-tratamiento.component';
import { DetallesVeterinarioComponent } from './detalles-veterinario/detalles-veterinario.component';
import { CrearVeterinarioComponent } from './crear-veterinario/crear-veterinario.component';
import { ModificarVeterinarioComponent } from './modificar-veterinario/modificar-veterinario.component';
import { LoginVeterinarioComponent } from './login-veterinario/login-veterinario.component';
import { AsignarTratamientoComponent } from './asignar-tratamiento/asignar-tratamiento.component';


@NgModule({
  declarations: [
    AppComponent,
    AdminDashboardComponent,
    CrearMascotaComponent,
    CrearUsuarioComponent,
    DetallesMascotaComponent,
    DetallesUsuarioComponent,
    ErrorComponent,
    FindComponent,
    InstalacionesComponent,
    LoginUsuarioComponent,
    ModificarMascotaComponent,
    ModificarUsuarioComponent,
    MostrarTodasMascotasComponent,
    MostrarTodosUsuariosComponent,
    MostrarTodosVeterinariosComponent,
    PromocionesComponent,
    ServiciosComponent,
    FooterComponent,
    HeaderComponent,
    UsuarioExitosoComponent,
    AplicarTratamientoComponent,
    DetallesVeterinarioComponent,
    CrearVeterinarioComponent,
    ModificarVeterinarioComponent,
    LoginVeterinarioComponent,
    AsignarTratamientoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    FormsModule,
    HttpClientModule
],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
