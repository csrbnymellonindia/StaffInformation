import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SuccessdialogComponent } from '../successdialog/successdialog.component';
import { AuthService } from '../services/auth.service';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
@Component({
  selector: 'app-financialdata',
  templateUrl: './financialdata.component.html',
  styleUrls: ['./financialdata.component.scss']
})
export class FinancialdataComponent {
  myForm!: FormGroup;
  selectedRows: any;

  constructor(private formBuilder: FormBuilder,private httpclient:HttpClient,private authService:AuthService,private dialog:MatDialog,private router:Router) {
    this.selectedRows = this.router.getCurrentNavigation()?.extras.state?.['rows'];
    this.myForm = 
      this.formBuilder.group({
        staffId : [this.selectedRows.staffId],
        bankName: ['', Validators.required],
        bankIFSCCode: ['', Validators.required],
        bankAccountNumber: ['', Validators.required],
      });

  }
  onSubmit(){
    
    this.httpclient.post('http://localhost:8080/addTeacherFamilyFinancialDetails',this.myForm.value).subscribe((res)=>{
      this.dialog.open(SuccessdialogComponent,{disableClose:true,data:{message:'Added financial information of Staff'}});
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
