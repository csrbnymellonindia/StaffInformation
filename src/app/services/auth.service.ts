import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() {
    if(localStorage.getItem('token')){
      this.isLoggedVar.next(true);
    }else{
      this.isLoggedVar.next(false);
    }
   }
  isLoggedVar = new BehaviorSubject<boolean>(false);
  $loggedInStatus = this.isLoggedVar.asObservable();
  isLoggedIn(){
    if(localStorage.getItem('token')){
      return true;
    }else{
      return false;
    }
  }
}
