import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudMovimientoComponent } from './crud-movimiento.component';

describe('CrudMovimientoComponent', () => {
  let component: CrudMovimientoComponent;
  let fixture: ComponentFixture<CrudMovimientoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CrudMovimientoComponent]
    });
    fixture = TestBed.createComponent(CrudMovimientoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
