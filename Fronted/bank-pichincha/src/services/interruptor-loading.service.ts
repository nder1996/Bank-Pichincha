import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoadingService } from './loading.service';
import { finalize, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InterruptorLoadingService implements HttpInterceptor{

  constructor(private loadingService: LoadingService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.loadingService.startLoading();
    return next.handle(req).pipe(
      finalize(() => this.loadingService.stopLoading()) // Finaliza el spinner al completarse la solicitud
    );
  }
}
