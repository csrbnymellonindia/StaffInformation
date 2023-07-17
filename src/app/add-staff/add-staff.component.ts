import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SuccessdialogComponent } from '../successdialog/successdialog.component';
import { AuthService } from '../services/auth.service';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-staff',
  templateUrl: './add-staff.component.html',
  styleUrls: ['./add-staff.component.scss']
})
export class AddStaffComponent {
  myForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private httpclient:HttpClient,
    private authService:AuthService,
    private dialog:MatDialog,
    private router:Router) {}

  ngOnInit() {
    this.myForm = this.formBuilder.group({
      staffName: ['', Validators.required],
      primaryContactNumber: ['', Validators.required],
      secondaryContactNumber: ['', Validators.required],
      address: ['', Validators.required],
      emailId: ['', [Validators.required, Validators.email]],
      whatsappNumber: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.myForm.valid) {
      // Handle form submission
      this.httpclient.post('http://localhost:8080/createTeacher',this.myForm.value,{observe:'response'}).subscribe((res:HttpResponse<any>)=>{
        if(res.status==200){
          this.dialog.open(SuccessdialogComponent,{disableClose:true,data:{message:'Added Staff'}});
          setTimeout(()=>{
            this.dialog.closeAll()
            this.authService.isLoggedVar.next(true);
          this.router.navigate(['/home'])
          },2000)
        }else{
          this.myForm.setErrors({serverError:true})
        }
      },(err)=>{
        console.log(err);
        this.myForm.setErrors({serverError:true})
      })
      
    } else {
      this.markFormGroupTouched(this.myForm);
    }

  }
  markFormGroupTouched(formGroup: FormGroup) {
    Object.values(formGroup.controls).forEach((control) => {
      control.markAsTouched();

      if (control instanceof FormGroup) {
        this.markFormGroupTouched(control);
      }
    });
  }
}
