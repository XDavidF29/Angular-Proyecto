import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Usuario } from '../models/Usuario';
import { UsuarioServicioService } from '../servicio/usuario-servicio.service';

@Component({
  selector: 'app-modificar-usuario',
  templateUrl: './modificar-usuario.component.html',
  styleUrls: ['./modificar-usuario.component.css']
})
export class ModificarUsuarioComponent implements OnInit {

  usuario: Usuario | undefined;

  constructor(
    private usuarioService: UsuarioServicioService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    // Obtiene el ID de la usuario desde la URL
    const id = Number(this.route.snapshot.paramMap.get('id'));
    
    // Busca la usuario con el ID correspondiente y suscríbete para obtener los datos
    this.usuarioService.findById(id).subscribe(
      (data: Usuario) => {
        this.usuario = data;
      },
      error => {
        console.error('Error al buscar la usuario:', error);
      }
    );
  }

  modificarUsuario(): void {
    if (this.usuario) {
      // Llama al servicio de actualización y suscríbete para manejar el resultado
      this.usuarioService.update(this.usuario).subscribe(
        () => {
          alert('usuario modificada con éxito');
        },
        error => {
          console.error('Error al modificar la usuario:', error);
          alert('Error al modificar la usuario');
        }
      );
    } else {
      alert('No se pudo modificar la usuario porque no fue encontrada');
    }
  }
  
}
