import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EdicionCalificacionComponent } from './edicion-calificacion.component';

describe('EdicionCalificacionComponent', () => {
  let component: EdicionCalificacionComponent;
  let fixture: ComponentFixture<EdicionCalificacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EdicionCalificacionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EdicionCalificacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
