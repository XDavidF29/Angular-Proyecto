import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AplicarTratamientoComponent } from './aplicar-tratamiento.component';

describe('AplicarTratamientoComponent', () => {
  let component: AplicarTratamientoComponent;
  let fixture: ComponentFixture<AplicarTratamientoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AplicarTratamientoComponent]
    });
    fixture = TestBed.createComponent(AplicarTratamientoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
