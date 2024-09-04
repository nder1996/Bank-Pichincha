import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'MiCotización', pathMatch: 'full' },
  {
    path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuardService], children: [
      { path: 'panelIndicadores', component: PanelIndicadoresComponent },
      { path: 'marca', component: CrudMarcaComponent },
      { path: 'prueba', component: CrudPruebaComponent },
      { path: 'reactivo', component: CrudReactivoComponent },
      { path: 'equipo', component: CrudEquipoComponent },
      { path: 'gestionCotizaciones', component: CrudOrdenesCotizacionesComponent },
   //   { path: 'crudOrdenDetalleEquipo', component: CrudOrdenDetalleEquipoComponent },
      
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
