import { ChangeDetectorRef, Component, EventEmitter, Inject, Input, OnDestroy, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MovimientoService } from 'src/services/movimiento.service';
import { LoadingComponent } from '../loading/loading.component';
import { LoadingService } from 'src/services/loading.service';
import { MAT_DIALOG_DATA, MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { MovimientoModel, MovimientoResponse } from 'src/model/movimiento-model';
import { MatTableDataSource } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { AbstractControl, FormBuilder, FormControl, ReactiveFormsModule } from '@angular/forms';
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

@Component({
  selector: 'app-crud-movimiento',
  templateUrl: './crud-movimiento.component.html',
  styleUrls: ['./crud-movimiento.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class CrudMovimientoComponent implements OnInit, OnDestroy {


  displayedColumns: string[] = ['NÚMERO DE CUENTA', 'TIPO DE CUENTA', 'SALDO INICIAL', 'ESTADO', 'VALOR', 'MOVIMIENTO', 'EDITAR', 'ELIMINAR'];
  @Input() dataSource = new MatTableDataSource<MovimientoModel>();
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  // @Input() dataSource = new MatTableDataSource<CrudOrdenesCotizacionesDetallesEquiposModel>();


  constructor(private dialog: MatDialog, private notificacionService: NotificacionService,
    private movimientoService: MovimientoService, private cdr: ChangeDetectorRef,
    private loadingService: LoadingService) { }

  private subscription!: Subscription;
  public listMovimiento: MovimientoModel[] = [];
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
    // Asumamos que tienes un método para obtener los datos de la tabla
    this.getAllMovimiento();

    this.dataSource.data = [];
    this.dataSource.data = this.listMovimiento; // Lista de datos que has cargado

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
          ///this.dataSource.data = this.listMovimiento;
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
          this.listMovimiento = response.data.Movimientos;
          this.dataSource.data = this.listMovimiento;
        },
        (error) => {
          //console.error('Error al obtener movimientos', error);
        }
      );
  }

  public getByMovimiento(idMovimiento: number) {
    this.movimiento = new MovimientoModel();
    this.subscription = this.movimientoService.getByMovimiento(idMovimiento)
      .subscribe(
        (response: any) => {
          this.movimiento = response.data.movimiento;
          //this.dataSource.data = this.listMovimiento;
        },
        (error) => {
          //console.error('Error al obtener movimientos', error);
        }
      );
  }

  public deleteByMovimiento(idMovimiento: number) {
    this.movimiento = new MovimientoModel();
    this.subscription = this.movimientoService.deleteMovimiento(idMovimiento)
      .subscribe(
        (response: any) => {
          //this.movimiento =response.data.movimiento;
          this.notificacionService.openDialog('info', 'info', response.data.movimiento);
          this.getAllMovimiento();
        },
        (error) => {
          this.notificacionService.openDialog('info', 'info', error);
        }
      );
  }




  dialogAgregarMovimiento(tipoModal: string) {
    const dialogRef = this.dialog.open(dialogOpciones, {
      width: '500px',
      disableClose: false
    });

    dialogRef.componentInstance.movimiento.subscribe((movimiento: MovimientoModel[]) => {
      this.dataSource.data = [];
      // this.dataSource.data.push(movimiento);
      this.dataSource.paginator = this.paginator;
      this.cdr.detectChanges();
      dialogRef.close();
    });
  }

  dialogEditarMovimiento(tipoModal: string) {
    const dialogRef = this.dialog.open(dialogOpciones, {
      width: '500px',
      disableClose: false
    });

    dialogRef.componentInstance.movimiento.subscribe((movimiento: MovimientoModel[]) => {
      this.dataSource.data = [];
      // this.dataSource.data.push(movimiento);
      this.dataSource.paginator = this.paginator;
      this.cdr.detectChanges();
      dialogRef.close();
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
  constructor(@Inject(MAT_DIALOG_DATA) public data: { listaMovimiento: MovimientoModel[] },
    private formBuilder: FormBuilder, private dialogRef: MatDialogRef<dialogOpciones>,
    private notificacionService: NotificacionService, private router: Router,
    private movimientoService: MovimientoService) { }

  public movimiento: EventEmitter<MovimientoModel> = new EventEmitter<MovimientoModel>();
  private subscription!: Subscription; 
  public listTipoMovimientos = [] = [];


  public saveMovimiento(movimiento: MovimientoModel) {
    this.subscription = this.movimientoService.saveMovimiento(movimiento)
      .subscribe(
        (response: any) => {
         // this.listMovimiento = response.data.Movimientos;
          ///this.dataSource.data = this.listMovimiento;
        },
        (error) => {
          //console.error('Error al obtener movimientos', error);
        }
      );
  }


  
  public getAllTipoMovimiento() {
    this.listTipoMovimientos = []
    this.subscription = this.movimientoService.getAllMovimientos()
      .subscribe(
        (response: any) => {
          this.listTipoMovimientos = response.data.tipoMovimiento;
          ///this.dataSource.data = this.listMovimiento;
        },
        (error) => {
          //console.error('Error al obtener movimientos', error);
        }
      );
  }

}
