import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() {
    if(sessionStorage.getItem('token')){
      this.userTypeVar.next(sessionStorage.getItem('type')!)
      this.isLoggedVar.next(true);
    }else{
      this.userTypeVar.next(sessionStorage.getItem('')!)
      this.isLoggedVar.next(false);
    }
   }
  userTypeVar = new BehaviorSubject<String>('');
  $userTypeObs = this.userTypeVar.asObservable();
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
