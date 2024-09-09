import { ChangeDetectorRef, Component, EventEmitter, Inject, Input, OnDestroy, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MovimientoService } from 'src/services/movimiento.service';
import { LoadingService } from 'src/services/loading.service';
import { MAT_DIALOG_DATA, MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { MovimientoModel } from 'src/model/movimiento-model';
import { MatTableDataSource } from '@angular/material/table';
import { CommonModule, DecimalPipe, formatCurrency } from '@angular/common';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSliderModule } from '@angular/material/slider';
import { Router } from '@angular/router';
import { NotificacionService } from 'src/services/notificacion.service';
import { Subscription } from 'rxjs';
import { TipoMovimiento } from 'src/model/tipo-movimiento.model';
import { PrecioFormatService } from 'src/services/precio-format.service';
import { ApiResponse } from 'src/model/api-response.model';
import { CuentaModel } from 'src/model/cuenta-models';

@Component({
  selector: 'app-crud-movimiento',
  templateUrl: './crud-movimiento.component.html',
  styleUrls: ['./crud-movimiento.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class CrudMovimientoComponent implements OnInit, OnDestroy {


  displayedColumns: string[] = ['NÚMERO DE CUENTA', 'TIPO DE CUENTA', 'SALDO INICIAL', 'ESTADO', 'VALOR', 'MOVIMIENTO', 'EDITAR', 'ELIMINAR'];
  @Input() dataSource = new MatTableDataSource<any>();
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  // @Input() dataSource = new MatTableDataSource<CrudOrdenesCotizacionesDetallesEquiposModel>();


  constructor(private dialog: MatDialog, private notificacionService: NotificacionService,
    private movimientoService: MovimientoService, private cdr: ChangeDetectorRef,
    private loadingService: LoadingService, private fb: FormBuilder) { }

  private subscription!: Subscription;
  public listMovimiento: any[] = [];
  public movimiento: MovimientoModel = new MovimientoModel();



  public cuenta = new FormControl('');
  public tipoCuenta = new FormControl('');
  public tipoMovimiento = new FormControl('');
  public valor = new FormControl('');
  public saldo = new FormControl('');
  public estado = new FormControl('');


  applyFilter() {
    const filterValues = {
      cuenta: this.cuenta.value,
      tipoCuenta: this.tipoCuenta.value,
      tipoMovimiento: this.tipoMovimiento.value,
      valor: this.valor.value,
      saldo: this.saldo.value,
      estado: this.estado.value,
    };

    this.dataSource.filter = JSON.stringify(filterValues);
  }



  updateTableMovimientos() {
    this.getAllMovimiento();
    this.dataSource.data = [];
    this.dataSource.data = this.listMovimiento;
    console.log("movimiento : " + JSON.stringify(this.listMovimiento))

    this.dataSource.filterPredicate = (data, filter): boolean => {
      const filterObject = JSON.parse(filter);

      const cuentaFilter = (filterObject.cuenta || '').toLowerCase();
      const tipoCuentaFilter = (filterObject.tipoCuenta || '').toLowerCase();
      const tipoMovimientoFilter = (filterObject.tipoMovimiento || '').toLowerCase();
      const valorFilter = (filterObject.valor || '').toLowerCase();
      const saldoFilter = (filterObject.saldo || '').toLowerCase();
      const estadoFilter = (filterObject.estado || '').toLowerCase();

      const matchesCuenta = !cuentaFilter || data.cuenta?.toString().toLowerCase().includes(cuentaFilter);
      const matchesTipoCuenta = !tipoCuentaFilter || data.tipoCuenta?.toLowerCase().includes(tipoCuentaFilter);
      const matchesTipoMovimiento = !tipoMovimientoFilter || data.tipoMovimiento?.toLowerCase().includes(tipoMovimientoFilter);
      const matchesValor = !valorFilter || data.valor?.toString().toLowerCase().includes(valorFilter);
      const matchesSaldo = !saldoFilter || data.saldo?.toString().toLowerCase().includes(saldoFilter);
      const matchesEstado = !estadoFilter || data.estado?.toLowerCase().includes(estadoFilter);

      return (matchesCuenta ?? false) &&
        (matchesTipoCuenta ?? false) &&
        (matchesTipoMovimiento ?? false) &&
        (matchesValor ?? false) &&
        (matchesSaldo ?? false) &&
        (matchesEstado ?? false);

    };

    this.cuenta.valueChanges.subscribe(() => this.applyFilter());
    this.tipoCuenta.valueChanges.subscribe(() => this.applyFilter());
    this.tipoMovimiento.valueChanges.subscribe(() => this.applyFilter());
    this.valor.valueChanges.subscribe(() => this.applyFilter());
    this.saldo.valueChanges.subscribe(() => this.applyFilter());
    this.estado.valueChanges.subscribe(() => this.applyFilter());

    if (this.paginator) {
      this.dataSource.paginator = this.paginator;
    }
  }


  borrarTodosLosFiltros(): void {
    this.clearFilter(this.cuenta);
    this.clearFilter(this.tipoCuenta);
    this.clearFilter(this.tipoMovimiento);
    this.clearFilter(this.valor);
    this.clearFilter(this.saldo);
    this.clearFilter(this.estado);
  }


  clearFilterCuenta() {
    this.clearFilter(this.cuenta); // Restablecer y aplicar el filtro para 'detalle_id'
  }

  clearFilterTipoCuenta() {
    this.clearFilter(this.tipoCuenta); // Restablecer y aplicar el filtro para 'detalle_id'

  }

  clearFilterTipoMovimiento() {
    this.clearFilter(this.tipoMovimiento); // Restablecer y aplicar el filtro para 'detalle_id'

  }


  clearFilterValor() {
    this.clearFilter(this.valor); // Restablecer y aplicar el filtro para 'detalle_id'
  }

  clearFilterSaldo() {
    this.clearFilter(this.saldo); // Restablecer y aplicar el filtro para 'detalle_id'

  }

  clearFilterEstado() {
    this.clearFilter(this.estado); // Restablecer y aplicar el filtro para 'detalle_id'
  }



  clearFilter(control: AbstractControl): void {
    control.setValue(''); // Restablecer el valor del filtro
    this.applyFilter(); // Aplicar el filtro después de restablecer el valor
  }

  ngOnInit() {
    this.updateTableMovimientos();


  }

  ngOnDestroy(): void {
    throw new Error('Method not implemented.');
  }


  ngAfterViewInit() {

    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.cdr.detectChanges();
    console.log("datasource : " + JSON.stringify(this.dataSource.data))


  }




  public updateMovimiento(movimiento: MovimientoModel) {
    this.subscription = this.movimientoService.updateMovimiento(movimiento)
      .subscribe(
        (response: any) => {
          this.listMovimiento = response.data.Movimientos;
        },
        (error) => {
          //console.error('Error al obtener movimientos', error);
        }
      );
  }




  public getAllMovimiento() {
    this.subscription = this.movimientoService.getAllMovimientos()
      .subscribe(
        (response: any) => {
          if (response && response.data && response.data.Movimientos) {
            this.listMovimiento = response.data.Movimientos;
            this.dataSource.data = response.data.Movimientos;
            console.log("entro al metodo getAllMetodo "+JSON.stringify( this.dataSource.data))
          }
        },
        (error) => {
          this.notificacionService.openDialog('error', 'error_outline', error.details);
        }
      );
  }

  public getByMovimiento(idMovimiento: number) {
    this.movimiento = new MovimientoModel();
    this.subscription = this.movimientoService.getByMovimiento(idMovimiento)
      .subscribe(
        (response: any) => {
          this.movimiento = response.data.movimiento;
        },
        (error) => {
          this.notificacionService.openDialog('error', 'error_outline', error.details);
        }
      );
  }






  dialogAgregarMovimiento(tipoModal: string) {
    const dialogRef = this.dialog.open(dialogOpciones, {
      width: '500px',
      disableClose: false,
      data: { movimiento: this.listMovimiento, tipoModal: tipoModal }
    });
    dialogRef.afterClosed().subscribe(() => {
      this.getAllMovimiento();
    });
  }


  dialogEliminarMovimiento(idMovimiento:number,tipoModal: string) {
    const dialogRef = this.dialog.open(dialogOpciones, {
      width: '500px',
      disableClose: false,
      data: { idMovimiento: idMovimiento, tipoModal: tipoModal }
    });
    dialogRef.afterClosed().subscribe(() => {
      this.getAllMovimiento();
    });
  }  




  dialogEditarMovimiento(movimiento:any,tipoModal: string) {
    const dialogRef = this.dialog.open(dialogOpciones, {
      width: '500px',
      disableClose: false,
      data: { movimiento: movimiento, tipoModal: tipoModal }
    });

  }








}


@Component({
  selector: 'dialogOpciones',
  templateUrl: 'dialog-opciones.html',
  standalone: true,
  imports: [MatDialogModule, MatButtonModule, MatFormFieldModule, MatSelectModule,
    MatInputModule, ReactiveFormsModule, MatButtonToggleModule, CommonModule, MatAutocompleteModule, MatSliderModule, MatDialogModule],
  encapsulation: ViewEncapsulation.None,
})
export class dialogOpciones {
  constructor(@Inject(MAT_DIALOG_DATA) public data: { movimientos: MovimientoModel[], tipoModal: string , idMovimiento:number , movimiento:any},
    private fb: FormBuilder, private dialogRef: MatDialogRef<dialogOpciones>,
    private notificacionService: NotificacionService, private router: Router,
    private movimientoService: MovimientoService, private cdr: ChangeDetectorRef, private decimalPipe: DecimalPipe,
    private monedaService: PrecioFormatService) { }

  public movimiento: EventEmitter<MovimientoModel> = new EventEmitter<MovimientoModel>();
  private subscription!: Subscription;
  public ListTipoMovimiento: TipoMovimiento[] = [];
  public listTodosMovimiento: MovimientoModel[] = [];
  public formMovimiento!: FormGroup;
  public formMovimientoEditar!: FormGroup;
  public movimientoSave: MovimientoModel = new MovimientoModel();
  public saldoFinal: number = 0.0;
  public listCuentas: any[] = [];
  


  public saveMovimiento(movimiento: MovimientoModel) {
    this.subscription = this.movimientoService.saveMovimiento(movimiento)
      .subscribe(
        (response: any) => {
          if (response.data.Movimiento == "Movimiento Creado") {
            this.notificacionService.openDialog('success', 'check_circle', response.data.Movimiento);
          } else {
            this.notificacionService.openDialog('error', 'error_outline', 'Inténtalo nuevamente');
          }
        },
        (error) => {
          this.notificacionService.openDialog('error', 'error_outline', error.details);
        }
      );
  }



  public getAllTipoMovimiento() {
    this.ListTipoMovimiento = []
    this.subscription = this.movimientoService.getAllTipoMovimiento()
      .subscribe(
        (response: any) => {
          this.ListTipoMovimiento = response.data.tipoMovimiento;
        },
        (error) => {
          this.notificacionService.openDialog('error', 'error_outline', error.details);
        }
      );
  }


  public getAllCuentas() {
    this.listCuentas = []
    this.subscription = this.movimientoService.getAllCuenta()
      .subscribe(
        (response: any) => {
          this.listCuentas = response.data.cuentas;
          console.log("lista de cuentas : " + JSON.stringify(this.listCuentas))
        },
        (error) => {
          this.notificacionService.openDialog('error', 'error_outline', error.details);
        }
      );
  }



  ngOnInit() {
    this.formMovimiento = this.fb.group({
      numeroCuenta: [null, Validators.required],
      tipoMovimiento: [null, Validators.required],
      valorMovimiento: [null, [Validators.required, Validators.pattern('^[0-9]*$')]],
      saldo: [{ value: '', disabled: true }, Validators.required]
    });

    this.formMovimientoEditar = this.fb.group({
      numeroCuenta: [this.data.movimiento.numCuenta, Validators.required],
      tipoMovimiento: [Validators.required],
      valorMovimiento: [null, [Validators.required, Validators.pattern('^[0-9]*$')]],
      saldo: [{ value: '', disabled: true }, Validators.required]
    });
  }


  ngAfterViewInit() {
    setTimeout(() => {
      this.getAllTipoMovimiento();
      this.getAllCuentas();
      this.listTodosMovimiento = this.data.movimiento;
      console.log(JSON.stringify(this.listTodosMovimiento))
      this.formatSaldoCuenta();
      this.cdr.detectChanges();
    }, 100);
  }



  private formatSaldoCuenta() {
    this.formMovimiento.get('numeroCuenta')?.valueChanges.subscribe(
      (numCuenta: any) => {
        this.listCuentas.forEach(cuenta => {
          if (cuenta.NUMERO_CUENTA == numCuenta) {
            const SALDO_INICIAL = cuenta.SALDO_INICIAL ?? 0.0;
            this.formMovimiento.patchValue({
              saldo: this.monedaService.formatoUSD(SALDO_INICIAL.toString()),
            });
          }
        })
      }
    );
  }



  public movimientoBancarioRespuesta() {
    const saldo: number = parseFloat(this.formMovimiento.get('saldo')?.value.replace(/[^0-9.-]/g, ''));
    const idTipoMovimiento: number = this.formMovimiento.get('tipoMovimiento')?.value ?? -1;
    if (saldo && idTipoMovimiento != -1) {
      const valorMovimiento = +this.formMovimiento.get('valorMovimiento')?.value.replace(/[^0-9.-]/g, '');
      this.ListTipoMovimiento.forEach(tipoMovimiento => {
        if (tipoMovimiento.idTipoMovimientos == idTipoMovimiento) {
          if (tipoMovimiento.nombre == 'RETIRO') {
            if (saldo < valorMovimiento) {
              this.notificacionService.openDialog('alert', 'warning', 'No se puede completar el retiro porque la cuenta no tiene suficiente saldo.');
              this.formMovimiento.patchValue({
                valorMovimiento: '',
              });
            } else {
              if(valorMovimiento>=1000){
                this.saldoFinal = saldo - valorMovimiento;
              }else{
                this.notificacionService.openDialog('alert', 'warning', 'Has excedido el límite diario de retiros permitido para esta cuenta');
                this.formMovimiento.patchValue({
                  valorMovimiento: '',
                });
              }
            }
          } if (tipoMovimiento.nombre == 'DEPÓSITO') {
            this.saldoFinal = saldo + valorMovimiento;
          }
        }
      })
    } else {
      this.notificacionService.openDialog('alert', 'warning', 'Asegúrate de tener saldo suficiente y de haber seleccionado un tipo de movimiento.');
      this.formMovimiento.patchValue({
        valorMovimiento: '',
      });
    }
  }


  guardarMovimiento() {
    if (this.formMovimiento.valid) {
      this.movimientoSave.fecha = new Date();
      this.movimientoSave.idTipoMovimiento = this.formMovimiento.get('tipoMovimiento')?.value;
      this.movimientoSave.numCuenta = this.formMovimiento.get('numeroCuenta')?.value;
      this.movimientoSave.saldo = this.saldoFinal;
      this.movimientoSave.valor = this.formMovimiento.get('valorMovimiento')?.value;
      this.movimientoSave.estado = 'TRUE';
      this.saveMovimiento(this.movimientoSave);
      this.dialogRef.close();
    }
  }









  public deleteByMovimiento() {
    this.subscription = this.movimientoService.deleteMovimiento(this.data.idMovimiento)
      .subscribe(
        (response: any) => {
          this.notificacionService.openDialog('info', 'info', response.data.movimiento);
        },
        (error) => {
          this.notificacionService.openDialog('error', 'error_outline', error.details);
        }
      );
      this.dialogRef.close()
  }

}
