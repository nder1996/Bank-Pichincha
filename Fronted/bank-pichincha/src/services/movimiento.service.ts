import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { enviroment } from 'src/enviroments/enviroment';
import { MovimientoModel } from 'src/model/movimiento-model';

@Injectable({
  providedIn: 'root'
})
export class MovimientoService {

  constructor(private httpClient: HttpClient) { }

  public movimientoUrl:string = enviroment.apiRestURL + '/movimientos'



  public getAllMovimientos(): Observable<MovimientoModel[]> {
    return this.httpClient.get<MovimientoModel[]>(this.movimientoUrl + `/getAllMovimientos`);
  }

  public getByMovimiento(idMovimiento: number): Observable<any> {
    return this.httpClient.get<any>(`${this.movimientoUrl}/getByMovimiento/${idMovimiento}`);
  }

  public saveMovimiento(movimiento: MovimientoModel): Observable<any> {
    return this.httpClient.post<any>(`${this.movimientoUrl}/save`, movimiento);
  }


  public updateMovimiento(movimiento: MovimientoModel): Observable<any> {
    return this.httpClient.patch<any>(`${this.movimientoUrl}/update`, movimiento);
  }

  public deleteMovimiento(idMovimiento: number): Observable<any> {
    return this.httpClient.delete<any>(`${this.movimientoUrl}/delete/${idMovimiento}`);
  }

  public getAllTipoMovimiento(): Observable<any> {
    return this.httpClient.get<any>(`${this.movimientoUrl}/getAllTipoMovimiento`);
  }

}
