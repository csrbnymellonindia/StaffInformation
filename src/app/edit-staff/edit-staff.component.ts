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
  medicalData: any = {
    "bloodGroup":"",
    "height":"",
    "weight":"",
    "differentlyAbled":""
  };
  financialData: any = {
    "bankName":"",
    "bankIFSCCode":"",
    "bankAccountNumber":""
  };

  constructor(private formBuilder: FormBuilder,private httpclient:HttpClient,private authService:AuthService,private dialog:MatDialog,private router:Router) {
    this.selectedRows = this.router.getCurrentNavigation()?.extras.state?.['rows'];
    
     
  }

  ngOnInit() {
    this.httpclient.get('http://localhost:8080/teacherMedicalDetails/'+this.selectedRows.staffId).subscribe((res)=>{
      this.medicalData = res;
      this.initFormControls();
    },(err)=>{
      console.log(err);
    })

    this.httpclient.get('http://localhost:8080/teacherFamilyFinancialDetails/'+this.selectedRows.staffId).subscribe((res)=>{
      this.financialData = res;
      this.initFormControls();
    },(err)=>{
      console.log(err);
    })
   
    this.myForm = this.formBuilder.group({
      staffId: [this.selectedRows.staffId],
      staffName: [this.selectedRows.staffName, Validators.required],
      primaryContactNumber: [this.selectedRows.primaryContactNumber, Validators.required],
      secondaryContactNumber: [this.selectedRows.secondaryContactNumber, Validators.required],
      address: [this.selectedRows.address, Validators.required],
      emailId: [this.selectedRows.emailId, [Validators.required, Validators.email]],
      whatsappNumber: [this.selectedRows.whatsappNumber, Validators.required],
      // Medical Information Fields
      bloodGroup: [this.medicalData.bloodGroup],
      height: [this.medicalData.height],
      weight: [this.medicalData.weight],
      differentlyAbled: [this.medicalData.differentlyAbled],
      disability: [this.medicalData.disability=="true"],
      // Financial Information Fields
      bankName: [this.financialData.bankName],
      bankIFSCCode: [this.financialData.bankIFSCCode],
      bankAccountNumber: [this.financialData.bankAccountNumber],
    });

    
  }
  private initFormControls() {
    if (this.medicalData) {
      this.myForm.patchValue({
        bloodGroup: this.medicalData.bloodGroup,
        height: this.medicalData.height,
        weight: this.medicalData.weight,
        differentlyAbled: this.medicalData.differentlyAbled,
        disability: this.medicalData.disability == 'true',
      });
    }
  
    if (this.financialData) {
      this.myForm.patchValue({
        bankName: this.financialData.bankName,
        bankIFSCCode: this.financialData.bankIFSCCode,
        bankAccountNumber: this.financialData.bankAccountNumber,
      });
    }
  }
  onSubmit() {
    if (this.myForm.valid) {
      let success = 0;
      
      // Handle form submission
      this.httpclient.put('http://localhost:8080/updateTeacher/'+this.myForm.value.staffId.toString(),this.myForm.value,{observe:'response'}).subscribe((res:HttpResponse<any>)=>{
        if(res.status==200){
          success+=1;
        }else{
          this.myForm.setErrors({serverError:true})
        }
      },(err)=>{
        console.log(err);
        this.myForm.setErrors({serverError:true})
      })

      this.httpclient.put('http://localhost:8080/updateTeacherFamilyFinancialDetails/'+this.myForm.value.staffId.toString(),this.myForm.value).subscribe((res)=>{
        success+=1;
      },(err)=>{
        console.log(err);
      })

      this.httpclient.put('http://localhost:8080/updateTeacherMedicalHistory/'+this.myForm.value.staffId.toString(),this.myForm.value).subscribe((res)=>{
        success+=1;
        if(success==3){
          this.dialog.open(SuccessdialogComponent,{disableClose:true,data:{message:'Edited Staff'}});
          setTimeout(()=>{
            this.dialog.closeAll()
            this.authService.isLoggedVar.next(true);
          this.router.navigate(['/home'])
          },2000)
        }
      },(err)=>{
        console.log(err);
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
