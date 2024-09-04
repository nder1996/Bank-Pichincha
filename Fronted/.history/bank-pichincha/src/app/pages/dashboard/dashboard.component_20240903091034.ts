import { Component } from '@angular/core';
import { LocalStorageService } from 'src/services/local-storage.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {


  constructor(private router: Router, private cdr: ChangeDetectorRef, private localStorageService: LocalStorageService) {

  showFiller = true;

}
