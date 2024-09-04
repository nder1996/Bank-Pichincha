import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { CrudClienteComponent } from './components/crud-cliente/crud-cliente.component';
import { CrudCuentaComponent } from './components/crud-cuenta/crud-cuenta.component';
import { CrudMovimientoComponent } from './components/crud-movimiento/crud-movimiento.component';
import { LoadingComponent } from './components/loading/loading.component';
import { NotificacionMensajeComponent } from './components/notificacion-mensaje/notificacion-mensaje.component';

const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  {
    path: 'dashboard', component: DashboardComponent, children: [
      { path: 'modulo-cliente', component: CrudClienteComponent },
      { path: 'modulo-cuenta', component: CrudCuentaComponent },
      { path: 'modulo-movimiento', component: CrudMovimientoComponent },
      { path: 'loading', component: LoadingComponent },
    ]
  },
  { path: 'modulo-cliente', component: CrudClienteComponent },
  { path: 'modulo-cuenta', component: CrudCuentaComponent },
  { path: 'modulo-movimiento', component: CrudMovimientoComponent },
  { path: 'notificacion', component: NotificacionMensajeComponent }  ,    
  { path: 'loading', component: LoadingComponent }    

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
