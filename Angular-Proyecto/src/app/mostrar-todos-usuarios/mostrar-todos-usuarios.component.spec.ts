import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarTodosUsuariosComponent } from './mostrar-todos-usuarios.component';

describe('MostrarTodosUsuariosComponent', () => {
  let component: MostrarTodosUsuariosComponent;
  let fixture: ComponentFixture<MostrarTodosUsuariosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MostrarTodosUsuariosComponent]
    });
    fixture = TestBed.createComponent(MostrarTodosUsuariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
