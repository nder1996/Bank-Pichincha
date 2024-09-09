export class MovimientoModel {
  idMovimientos?: number;
  numCuenta?: string;
  fecha?: Date;
  idTipoMovimiento?: number;
  valor?: number;
  saldo?: number;
  estado?: string;

  constructor(
    idMovimientos?: number,
    numCuenta?: string,
    idTipoMovimiento?: number,
    valor?: number,
    fecha?: Date,
    saldo?: number,
    estado?: string,
  ) {
    this.idMovimientos = idMovimientos;
    this.numCuenta = numCuenta;
    this.fecha = fecha;
    this.idTipoMovimiento = idTipoMovimiento;
    this.valor = valor;
    this.saldo = saldo;
    this.fecha = fecha;
    this.estado = estado;
  }
}
