export class CuentaModel {
  NUMERO_CUENTA?: number;
  SALDO_INICIAL?: number;
  CLIENTE?: string;
  ESTADO?: string;
  TIPO_CUENTA?: number;

  constructor(
    NUMERO_CUENTA?: number,
    SALDO_INICIAL?: number,
    CLIENTE?: string,
    ESTADO?: string,
    TIPO_CUENTA?: number
  ) {
    this.NUMERO_CUENTA = NUMERO_CUENTA;
    this.SALDO_INICIAL = SALDO_INICIAL;
    this.CLIENTE = CLIENTE;
    this.ESTADO = ESTADO;
    this.TIPO_CUENTA = TIPO_CUENTA;
  }
}
