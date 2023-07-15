import { Injectable, inject } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  createUrlTreeFromSnapshot,
} from '@angular/router';
import { map } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    constructor(
        private router: Router,
        private authService: AuthService
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      let flag = false;  
      this.authService.$loggedInStatus.subscribe((res)=>{
          flag = res;
        });
        if(flag){
          return true;
        }else{
          this.router.navigate(['/login']);
          return false;
        }
       

        // not logged in so redirect to login page with the return url
        
    }
}