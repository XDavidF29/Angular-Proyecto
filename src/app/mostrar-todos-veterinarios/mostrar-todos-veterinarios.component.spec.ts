import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarTodosVeterinariosComponent } from './mostrar-todos-veterinarios.component';

describe('MostrarTodosVeterinariosComponent', () => {
  let component: MostrarTodosVeterinariosComponent;
  let fixture: ComponentFixture<MostrarTodosVeterinariosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MostrarTodosVeterinariosComponent]
    });
    fixture = TestBed.createComponent(MostrarTodosVeterinariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
