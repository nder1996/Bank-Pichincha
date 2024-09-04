export class MovimientoModel {
  idMovimientos?: number;
  idCuenta?: number;
  cuenta?: string;
  idTipoCuenta?: number;
  tipoCuenta?: string;
  fecha?: Date;
  idTiposMovimientos?: number;
  tipoMovimiento?: string;
  valor?: number;
  saldo?: number;
  estado?: string;
  createAt?: Date;
  updateAt?: Date;

  constructor(
    idMovimientos?: number,
    idCuenta?: number,
    cuenta?: string,
    idTipoCuenta?: number,
    tipoCuenta?: string,
    fecha?: Date,
    idTiposMovimientos?: number,
    tipoMovimiento?: string,
    valor?: number,
    saldo?: number,
    estado?: string,
    createAt?: Date,
    updateAt?: Date
  ) {
    this.idMovimientos = idMovimientos;
    this.idCuenta = idCuenta;
    this.cuenta = cuenta;
    this.idTipoCuenta = idTipoCuenta;
    this.tipoCuenta = tipoCuenta;
    this.fecha = fecha;
    this.idTiposMovimientos = idTiposMovimientos;
    this.tipoMovimiento = tipoMovimiento;
    this.valor = valor;
    this.saldo = saldo;
    this.estado = estado;
    this.createAt = createAt;
    this.updateAt = updateAt;
  }
}

export interface MovimientoResponse {
  data: {
    Movimientos: MovimientoModel[];
  };
  meta: {
    message: string;
    status: number;
  };
  error: any;
}