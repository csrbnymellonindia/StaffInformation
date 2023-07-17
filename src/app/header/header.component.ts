import { AuthService } from './../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit{
  public isLoggedIn: boolean = false;
  public isAdmin: boolean = false;
  constructor(private router:Router,private authservice:AuthService){

  }
  ngOnInit(){
    this.authservice.$loggedInStatus.subscribe((res)=>{
      this.isLoggedIn = res;
    });
    this.authservice.$userTypeObs.subscribe((res)=>{
      console.log(res);
      
      this.isAdmin = res=='admin';
    });
  }
  logout(){
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('username');
    this.authservice.isLoggedVar.next(false);
    this.router.navigate(['/logout']);
  }
}
