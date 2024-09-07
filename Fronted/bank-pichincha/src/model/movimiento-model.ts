export class MovimientoModel {
  idMovimientos?: number;
  numCuenta?: number;
  fecha?: Date;
  idTipoMovimiento?: number;
  valor?: number;
  saldo?: number;
  estado?: string;

  constructor(
    idMovimientos?: number,
    numCuenta?: number,
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
