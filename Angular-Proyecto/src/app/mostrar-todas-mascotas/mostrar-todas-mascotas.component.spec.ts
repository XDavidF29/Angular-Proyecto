import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarTodasMascotasComponent } from './mostrar-todas-mascotas.component';

describe('MostrarTodasMascotasComponent', () => {
  let component: MostrarTodasMascotasComponent;
  let fixture: ComponentFixture<MostrarTodasMascotasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MostrarTodasMascotasComponent]
    });
    fixture = TestBed.createComponent(MostrarTodasMascotasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
