import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { CrudClienteComponent } from './components/crud-cliente/crud-cliente.component';
import { CrudCuentaComponent } from './components/crud-cuenta/crud-cuenta.component';
import { CrudMovimientoComponent } from './components/crud-movimiento/crud-movimiento.component';
import { LoadingComponent } from './components/loading/loading.component';
import { NotificacionMensajeComponent } from './components/notificacion-mensaje/notificacion-mensaje.component';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { MatDialog } from '@angular/material/dialog';
import { ReactiveFormsModule } from '@angular/forms';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { InterruptorLoadingService } from 'src/services/interruptor-loading.service';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    CrudClienteComponent,
    CrudCuentaComponent,
    CrudMovimientoComponent,
    LoadingComponent,
    NotificacionMensajeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule, MatIconModule,
    MatToolbarModule, MatTableModule, MatSidenavModule, MatSelectModule,
    MatRadioModule, MatPaginatorModule, MatListModule, MatButtonModule,
    MatInputModule, MatFormFieldModule, MatExpansionModule, MatDialogModule,
    MatDividerModule, MatDatepickerModule, MatCardModule, MatButtonToggleModule, HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
  { provide: MatDialogRef, useValue: {} },
  { provide: MAT_DIALOG_DATA, useValue: {} },
  { provide: LocationStrategy, useClass: HashLocationStrategy },
  {
    provide: HTTP_INTERCEPTORS,
    useClass: InterruptorLoadingService,
    multi: true
  }
    
],
  bootstrap: [AppComponent]
})
export class AppModule { }
