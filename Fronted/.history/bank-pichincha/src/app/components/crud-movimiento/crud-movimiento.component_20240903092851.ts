import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-crud-movimiento',
  templateUrl: './crud-movimiento.component.html',
  styleUrls: ['./crud-movimiento.component.css']
})
export class CrudMovimientoComponent {


  displayedColumns: string[] = ['Numero de cuenta', 'Cliente', 'Tipo', 'Saldo Inicial', 'Estado', 'Movimiento'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;




  ngOnInit() {

  }


  ngAfterViewInit() {


}
