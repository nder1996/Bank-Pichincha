import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { enviroment } from 'src/enviroments/enviroment';
import { CuentaModel } from 'src/model/cuenta-models';

@Injectable({
  providedIn: 'root'
})
export class CuentaService {

  constructor(private httpClient: HttpClient) { }

  public movimientoUrl:string = enviroment.apiRestURL + '/cuentas'

  public getAllCuenta(): Observable<CuentaModel[]> {
    return this.httpClient.get<CuentaModel[]>(this.movimientoUrl + `/getAllCuentas`);
  }   

  
}
