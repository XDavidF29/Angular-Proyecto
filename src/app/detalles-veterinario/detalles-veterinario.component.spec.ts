import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetallesVeterinarioComponent } from './detalles-veterinario.component';

describe('DetallesVeterinarioComponent', () => {
  let component: DetallesVeterinarioComponent;
  let fixture: ComponentFixture<DetallesVeterinarioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetallesVeterinarioComponent]
    });
    fixture = TestBed.createComponent(DetallesVeterinarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
