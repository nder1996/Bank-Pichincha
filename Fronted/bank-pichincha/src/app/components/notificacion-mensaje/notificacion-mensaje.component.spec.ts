import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotificacionMensajeComponent } from './notificacion-mensaje.component';

describe('NotificacionMensajeComponent', () => {
  let component: NotificacionMensajeComponent;
  let fixture: ComponentFixture<NotificacionMensajeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NotificacionMensajeComponent]
    });
    fixture = TestBed.createComponent(NotificacionMensajeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
