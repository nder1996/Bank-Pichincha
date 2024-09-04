import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { NotificacionMensajeComponent } from 'src/app/components/notificacion-mensaje/notificacion-mensaje.component';

@Injectable({
  providedIn: 'root'
})
export class NotificacionService {

  constructor(private dialog: MatDialog) { }
  //this.dialogService.openDialog('info', 'info', 'Este es un mensaje de notificación.'); informativo
  //this.dialogService.openDialog('success', 'check_circle', 'Este es un mensaje de notificación.'); //exito
  //this.dialogService.openDialog('alert', 'warning', 'Este es un mensaje de notificación.'); //alert
  //this.notificacionMessageService.openDialog('error', 'error_outline', 'Este es un mensaje de notificación.'); //error

  openDialog(tipo: string, icon: string, mensaje: string): void {
    const dialogRef: MatDialogRef<NotificacionMensajeComponent> =
      this.dialog.open(NotificacionMensajeComponent, {
        width: '550px',
        position: { top: '20px', right: '20px' },
        data: { tipo, icon, mensaje },
        backdropClass: 'first-modal-backdrop',
        hasBackdrop: false
      });
  
    setTimeout(() => {
      dialogRef.close();
    }, 3500);
  }
  

}
