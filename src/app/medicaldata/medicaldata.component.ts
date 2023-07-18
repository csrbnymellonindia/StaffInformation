import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SuccessdialogComponent } from '../successdialog/successdialog.component';
import { AuthService } from '../services/auth.service';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
// "staffId":"2",
// "bloodGroup":"A+ve",
// "height":"160",
// "weight":"50",
// "differentlyAbled":"true",
// "disability":"Visually impaired"
//     
@Component({
  selector: 'app-medicaldata',
  templateUrl: './medicaldata.component.html',
  styleUrls: ['./medicaldata.component.scss']
})
export class MedicaldataComponent {
  myForm!: FormGroup;
  selectedRows: any;

  constructor(private formBuilder: FormBuilder,private httpclient:HttpClient,private authService:AuthService,private dialog:MatDialog,private router:Router) {
    this.selectedRows = this.router.getCurrentNavigation()?.extras.state?.['rows'];
    this.myForm = this.formBuilder.group({
      staffId : [this.selectedRows.staffId],
      bloodGroup: ['', Validators.required],
      height: ['', Validators.required],
      weight: ['', Validators.required],
      differentlyAbled: [false],
      disability: [''],
    });

  }
  onSubmit(){
    const differentlyAbled = this.myForm.get('differentlyAbled')?.value.toString();
    this.myForm.patchValue({
      differentlyAbled
    })
    console.log(this.myForm.value);
    
    this.httpclient.post('http://localhost:8080/addTeacherMedicalHistory',this.myForm.value).subscribe((res)=>{
      this.dialog.open(SuccessdialogComponent,{disableClose:true,data:{message:'Added medical information of Staff'}});
          setTimeout(()=>{
            this.dialog.closeAll()
            this.authService.isLoggedVar.next(true);
          this.router.navigate(['/home'])
          },2000)
    },(err)=>{
      console.log(err);
      
    })
  }
}
