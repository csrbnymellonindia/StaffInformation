import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EnvService } from '../env.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from '../services/auth.service';
import { SuccessdialogComponent } from '../successdialog/successdialog.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  message:any;
  constructor(private formBuilder: FormBuilder,private httpclient:HttpClient,private envservice:EnvService,private router:Router,private dialog:MatDialog,private authservice:AuthService) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(4)]]
    });
  }

  get passwordControl() {
    return this.loginForm.get('password');
  }

  login(): void {
    if (this.loginForm.valid) {
      const password = this.loginForm.get('password')?.value;
      const username = this.loginForm.get('username')?.value
      // Perform login logic
      
      this.httpclient.post('http://localhost:8080/validate',{
        "username":username,
        "password":password
      }).subscribe(async (res:any)=>{
       await sessionStorage.setItem('token',res.token);
       await sessionStorage.setItem('username',username);
       await sessionStorage.setItem('type',res.userType)
       this.dialog.open(SuccessdialogComponent,{disableClose:true});
        setTimeout(()=>{
          this.dialog.closeAll()
          this.authservice.isLoggedVar.next(true);
        this.router.navigate(['/home'])
        },2000)
      },(err:any)=>{
        this.loginForm.get('password')?.setErrors({serverError:true})
        this.loginForm.get('username')?.setErrors({serverError:true})
        this.message = "Invalid credentials"
        console.log(err);
        
      });
  }
}
}

