import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioExitosoComponent } from './usuario-exitoso.component';

describe('UsuarioExitosoComponent', () => {
  let component: UsuarioExitosoComponent;
  let fixture: ComponentFixture<UsuarioExitosoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UsuarioExitosoComponent]
    });
    fixture = TestBed.createComponent(UsuarioExitosoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
