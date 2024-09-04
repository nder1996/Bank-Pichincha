export class TipoMovimiento {
    idTipoMovimientos?: number;
    nombre?: string;
    estado?: string;
  
    constructor(idTipoMovimientos?: number, nombre?: string, estado?: string) {
      this.idTipoMovimientos = idTipoMovimientos;
      this.nombre = nombre;
      this.estado = estado;
    }
  }
  