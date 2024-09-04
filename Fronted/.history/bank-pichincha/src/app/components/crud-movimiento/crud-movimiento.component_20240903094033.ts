import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MovimientoService } from 'src/services/movimiento.service';
import { LoadingComponent } from '../loading/loading.component';
import { LoadingService } from 'src/services/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-crud-movimiento',
  templateUrl: './crud-movimiento.component.html',
  styleUrls: ['./crud-movimiento.component.css']
})
export class CrudMovimientoComponent {


  displayedColumns: string[] = ['NÚMERO DE CUENTA', 'CLIENTE', 'TIPO', 'SALDO INICIAL', 'ESTADO', 'MOVIMIENTO', 'EDITAR', 'ELIMINAR'];
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
