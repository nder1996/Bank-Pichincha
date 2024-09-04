export class Movimiet {
    id?: number;
    idEquipo?:number;
    existePrueba?:string;
    nombre?: string;
    descripcion?: string;
    estado?: string;
  
    constructor(
      id?: number,
      idEquipo?:number,
      nombre?: string,
      descripcion?: string,
      estado?: string,
      existePrueba?:string
    ) {
      this.id = id;
      this.nombre = nombre;
      this.descripcion = descripcion;
      this.estado = estado;
      this.idEquipo = idEquipo;
      this.existePrueba = existePrueba;
    }
  }