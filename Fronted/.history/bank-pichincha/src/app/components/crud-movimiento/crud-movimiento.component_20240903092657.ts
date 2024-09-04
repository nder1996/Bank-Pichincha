import { Component } from '@angular/core';

@Component({
  selector: 'app-crud-movimiento',
  templateUrl: './crud-movimiento.component.html',
  styleUrls: ['./crud-movimiento.component.css']
})
export class CrudMovimientoComponent {


  displayedColumns: string[] = ['Numero de cuenta', 'Cliente', 'Tipo', 'referenciaEquipo', 'descripcionEquipo', 'estado', 'urlImg', 'accion'];


}
