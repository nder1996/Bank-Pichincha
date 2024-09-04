import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-notificacion-mensaje',
  templateUrl: './notificacion-mensaje.component.html',
  styleUrls: ['./notificacion-mensaje.component.css']
})
export class NotificacionMensajeComponent {
  public data: any;

  constructor(
    public dialogRef: MatDialogRef<NotificacionMensajeComponent>,
    @Inject(MAT_DIALOG_DATA) dataFromDialog: any
  ) {
    this.data = dataFromDialog;
  }

  cerrarDialogo(): void {
    this.dialogRef.close();
  }

}
