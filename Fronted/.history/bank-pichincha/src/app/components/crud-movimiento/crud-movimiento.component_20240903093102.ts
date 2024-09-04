import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MovimientoService } from 'src/services/movimiento.service';
import { LoadingComponent } from '../loading/loading.component';
import { LoadingService } from 'src/services/loading.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-crud-movimiento',
  templateUrl: './crud-movimiento.component.html',
  styleUrls: ['./crud-movimiento.component.css']
})
export class CrudMovimientoComponent {


  displayedColumns: string[] = ['Numero de cuenta', 'Cliente', 'Tipo', 'Saldo Inicial', 'Estado', 'Movimiento'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;

 // @Input() dataSource = new MatTableDataSource<CrudOrdenesCotizacionesDetallesEquiposModel>();


  constructor(private dialog: MatDialog,
    private movimientoService: MovimientoService, private cdr: ChangeDetectorRef,
    private spinnerService: LoadingService) {}
  


  ngOnInit() {

  }


  ngAfterViewInit() {

  }





}
