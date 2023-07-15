import { AuthService } from './services/auth.service';
import { ListKeyManager } from '@angular/cdk/a11y';
import { Component, OnInit } from '@angular/core';
import { LicenseManager } from 'ag-grid-enterprise'
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  public isLoggedIn:boolean = false;
  constructor(private authservice:AuthService){

  }
  ngOnInit(): void {
    this.authservice.$loggedInStatus.subscribe((res)=>{
      this.isLoggedIn = res;
    })
    LicenseManager.setLicenseKey(' ')
  }
  title = 'frontend';

}
