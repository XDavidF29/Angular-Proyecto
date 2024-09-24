import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetallesMascotaComponent } from './detalles-mascota.component';

describe('DetallesMascotaComponent', () => {
  let component: DetallesMascotaComponent;
  let fixture: ComponentFixture<DetallesMascotaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetallesMascotaComponent]
    });
    fixture = TestBed.createComponent(DetallesMascotaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
