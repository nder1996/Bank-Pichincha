import { ChangeDetectorRef, Component, ViewChild, ViewEncapsulation } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MovimientoService } from 'src/services/movimiento.service';
import { LoadingComponent } from '../loading/loading.component';
import { LoadingService } from 'src/services/loading.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { MovimientoModel } from 'src/model/movimiento-model';
import { MatTableDataSource } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSliderModule } from '@angular/material/slider';

@Component({
  selector: 'app-crud-movimiento',
  templateUrl: './crud-movimiento.component.html',
  styleUrls: ['./crud-movimiento.component.css']
})
export class CrudMovimientoComponent {


  displayedColumns: string[] = ['NÚMERO DE CUENTA', 'CLIENTE', 'TIPO DE  MOVIMIENTO', 'SALDO INICIAL', 'ESTADO', 'EDITAR', 'ELIMINAR'];
  dataSource = new MatTableDataSource<MovimientoModel>([]);
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
 // @Input() dataSource = new MatTableDataSource<CrudOrdenesCotizacionesDetallesEquiposModel>();


  constructor(private dialog: MatDialog,
    private movimientoService: MovimientoService, private cdr: ChangeDetectorRef,
    private spinnerService: LoadingService) {}
  


  ngOnInit() {

  }


  ngAfterViewInit() {

  }


}


@Component({
  selector: 'dialog-opciones',
  templateUrl: 'dialog-opciones.html',
  standalone: true,
  imports: [MatDialogModule, MatButtonModule, MatFormFieldModule, MatSelectModule, MatInputModule, ReactiveFormsModule, MatButtonToggleModule, CommonModule, MatAutocompleteModule,MatSliderModule],
  encapsulation: ViewEncapsulation.None,
})
export class dialogOpciones {
  constructor(@Inject(MAT_DIALOG_DATA) public data: { equipoDetalle: CrudOrdenesCotizacionesDetallesEquiposModel, tipoModal: string, listEquipoModel: CotEquipoModel[], listCotMarcaModel: cotMarcaModel[] },
  private formBuilder: FormBuilder, private ordenesCrudService: CrudOrdenesCotizacionesService,
  private dialogRef: MatDialogRef<dialogEditarOrdenDetalleEquipo>, private toast: ToastrService,
  private filtroAutoCompleteService: AutoCompleteFiltroCotizacionService, private marcaService: CotMarcaService,
  private notificacionService: NotificacionMessageService, private router: Router,
  private variableGlobal : VariablesGlorablesService , private zonaPagosService:ZonaPagosService) {

  }
}
