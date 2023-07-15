import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SuccessdialogComponent } from '../successdialog/successdialog.component';
import { AuthService } from '../services/auth.service';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'edit-add-staff',
  templateUrl: './edit-staff.component.html',
  styleUrls: ['./edit-staff.component.scss']
})
export class EditStaffComponent {
  myForm!: FormGroup;
  selectedRows: any;

  constructor(private formBuilder: FormBuilder,private httpclient:HttpClient,private authService:AuthService,private dialog:MatDialog,private router:Router) {
    this.selectedRows = this.router.getCurrentNavigation()?.extras.state?.['rows'];

  }

  ngOnInit() {
     
    this.myForm = this.formBuilder.group({
      staffId: [this.selectedRows.staffId],
      staffName: [this.selectedRows.staffName, Validators.required],
      primaryContactNumber: [this.selectedRows.primaryContactNumber, Validators.required],
      secondaryContactNumber: [this.selectedRows.secondaryContactNumber, Validators.required],
      address: [this.selectedRows.address, Validators.required],
      emailId: [this.selectedRows.emailId, [Validators.required, Validators.email]],
      whatsappNumber: [this.selectedRows.whatsappNumber, Validators.required],
    });

    
  }

  onSubmit() {
    if (this.myForm.valid) {
      // Handle form submission
      this.httpclient.put('http://localhost:8080/updateTeacher/'+this.myForm.value.staffId.toString(),this.myForm.value,{observe:'response'}).subscribe((res:HttpResponse<any>)=>{
        if(res.status==200){
          this.dialog.open(SuccessdialogComponent,{disableClose:true,data:{message:'Edited Staff'}});
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
