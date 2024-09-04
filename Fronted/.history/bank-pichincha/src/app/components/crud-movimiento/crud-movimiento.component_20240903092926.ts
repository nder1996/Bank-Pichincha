import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MovimientoService } from 'src/services/movimiento.service';

@Component({
  selector: 'app-crud-movimiento',
  templateUrl: './crud-movimiento.component.html',
  styleUrls: ['./crud-movimiento.component.css']
})
export class CrudMovimientoComponent {


  displayedColumns: string[] = ['Numero de cuenta', 'Cliente', 'Tipo', 'Saldo Inicial', 'Estado', 'Movimiento'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private marcaService: CotMarcaService, private dialog: MatDialog,
    private equipoService: MovimientoService, private cdr: ChangeDetectorRef,
    private spinnerService: LoadingServices) {}
  


  ngOnInit() {

  }


  ngAfterViewInit() {

  }


}
