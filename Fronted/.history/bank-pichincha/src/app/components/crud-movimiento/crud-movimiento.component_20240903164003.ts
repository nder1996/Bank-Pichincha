import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MovimientoService } from 'src/services/movimiento.service';
import { LoadingComponent } from '../loading/loading.component';
import { LoadingService } from 'src/services/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { MovimientoModel } from 'src/model/movimiento-model';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-crud-movimiento',
  templateUrl: './crud-movimiento.component.html',
  styleUrls: ['./crud-movimiento.component.css']
})
export class CrudMovimientoComponent {


  displayedColumns: string[] = ['NÚMERO DE CUENTA', 'CLIENTE', 'TIPO DE  MOVIMIENTO', 'SALDO INICIAL', 'ESTADO', 'EDITAR', 'ELIMINAR'];
  dataSource = new MatTableDataSource<MovimientoModel>([]);
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
 // @Input() dataSource = new MatTableDataSource<CrudOrdenesCotizacionesDetallesEquiposModel>();


  constructor(private dialog: MatDialog,
    private movimientoService: MovimientoService, private cdr: ChangeDetectorRef,
    private spinnerService: LoadingService) {}
  


  ngOnInit() {

  }


  ngAfterViewInit() {

  }


}


@Component({
  selector: 'dialogEditarOrdenDetalleEquipo',
  templateUrl: 'dialog-ordenDetalle-equipoEdit.html',
  standalone: true,
  imports: [MatDialogModule, MatButtonModule, MatFormFieldModule, MatSelectModule, MatInputModule, ReactiveFormsModule, MatButtonToggleModule, CommonModule, ToastrModule, MatAutocompleteModule,MatSliderModule],
  encapsulation: ViewEncapsulation.None,
})
export class dialogEditarOrdenDetalleEquipo {
