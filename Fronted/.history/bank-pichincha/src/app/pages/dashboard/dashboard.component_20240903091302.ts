import { ChangeDetectorRef, Component } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from 'src/services/local-storage.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  constructor(private router: Router, private cdr: ChangeDetectorRef, private StorageService: LocalStorageService) {}
  
  private showFiller:boolean = true;


  ngOnInit() {

  }

  ngAfterViewInit() {

  }


  moduloCliente(){
    this.router.navigate(['/dashboard']);

  }

  moduloMovimiento(){

  }

  moduloCuenta(){

  }


}
