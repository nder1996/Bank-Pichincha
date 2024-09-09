import { ChangeDetectorRef, Component, Input, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, AbstractControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { CuentaModel } from 'src/model/cuenta-models';
import { MovimientoModel } from 'src/model/movimiento-model';
import { CuentaService } from 'src/services/cuenta.service';
import { LoadingService } from 'src/services/loading.service';
import { MovimientoService } from 'src/services/movimiento.service';
import { NotificacionService } from 'src/services/notificacion.service';

@Component({
  selector: 'app-crud-cuenta',
  templateUrl: './crud-cuenta.component.html',
  styleUrls: ['./crud-cuenta.component.css']
})
export class CrudCuentaComponent {

  displayedColumns: string[] = ['NÚMERO DE CUENTA', 'TIPO DE CUENTA', 'SALDO INICIAL', 'CLIENTE', 'ESTADO', 'EDITAR', 'ELIMINAR'];
  @Input() dataSource = new MatTableDataSource<any>();
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;



  constructor(private dialog: MatDialog, private notificacionService: NotificacionService,
    private movimientoService: MovimientoService, private cdr: ChangeDetectorRef,
    private loadingService: LoadingService, private fb: FormBuilder, private cuentaService: CuentaService) { }

  private subscription!: Subscription;
  public listaCuentas: any[] = [];
  public cuenta: CuentaModel = new CuentaModel();





  ngOnInit() {
    this.getAllCuentas();
  }

  ngOnDestroy(): void {
    throw new Error('Method not implemented.');
  }


  ngAfterViewInit() {

    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.cdr.detectChanges();



  }





  public getAllCuentas() {
    this.subscription = this.cuentaService.getAllCuenta()
      .subscribe(
        (response: any) => {
          if (response && response.data && response.data.cuentas) {
            this.listaCuentas = response.data.cuentas;
            this.dataSource.data = response.data.cuentas;
          }
        },
        (error) => {
          this.notificacionService.openDialog('error', 'error_outline', error.details);
        }
      );
  }




}




