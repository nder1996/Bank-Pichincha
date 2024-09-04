import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { CrudClienteComponent } from './components/crud-cliente/crud-cliente.component';
import { CrudCuentaComponent } from './components/crud-cuenta/crud-cuenta.component';
import { CrudMovimientoComponent } from './components/crud-movimiento/crud-movimiento.component';
import { LoadingComponent } from './components/loading/loading.component';
import { NotificacionMensajeComponent } from './components/notificacion-mensaje/notificacion-mensaje.component';

const routes: Routes = [
  { path: '', redirectTo: 'MiCotización', pathMatch: 'full' },
  {
    path: 'dashboard', component: DashboardComponent, children: [
      { path: 'cliente', component: CrudClienteComponent },
      { path: 'marca', component: CrudCuentaComponent },
      { path: 'prueba', component: CrudMovimientoComponent },
      { path: 'reactivo', component: LoadingComponent },
      { path: 'equipo', component: NotificacionMensajeComponent },
   //   { path: 'crudOrdenDetalleEquipo', component: CrudOrdenDetalleEquipoComponent },
      
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
