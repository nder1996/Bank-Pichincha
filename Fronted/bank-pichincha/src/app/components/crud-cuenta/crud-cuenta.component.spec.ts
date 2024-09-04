import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudCuentaComponent } from './crud-cuenta.component';

describe('CrudCuentaComponent', () => {
  let component: CrudCuentaComponent;
  let fixture: ComponentFixture<CrudCuentaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CrudCuentaComponent]
    });
    fixture = TestBed.createComponent(CrudCuentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
