import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EnvService } from '../env.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  message:any;
  constructor(private formBuilder: FormBuilder,private httpclient:HttpClient,private envservice:EnvService,private router:Router) { }

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
      }).subscribe((res:any)=>{
       sessionStorage.setItem('token',res.token);
       sessionStorage.setItem('username',username);
        this.router.navigate(['/home'])
      },(err:any)=>{
        this.loginForm.get('password')?.setErrors({serverError:true})
        this.loginForm.get('username')?.setErrors({serverError:true})
        this.message = "Invalid credentials"
        console.log(err);
        
      });
  }
}
}
