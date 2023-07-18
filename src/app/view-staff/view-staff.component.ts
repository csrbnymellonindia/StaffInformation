import { HttpClient } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-staff',
  templateUrl: './view-staff.component.html',
  styleUrls: ['./view-staff.component.scss']
})
export class ViewStaffComponent {
  medicalData:any;
  financialData:any;
  staffName!: String;
  primaryContactNum!: String;
  secondaryContactNum!: String;
  staffEmail!: String;
  staffWAnum!: String;
  staffAddress!: String;
  staffProfileURL!: String;
  selectedRows: any;

  constructor(private router:Router,private httpclient:HttpClient){
    
    this.selectedRows = this.router.getCurrentNavigation()?.extras.state?.['rows'];
    this.staffName = this.selectedRows.staffName;
    this.primaryContactNum = this.selectedRows.primaryContactNumber;
    this.secondaryContactNum = this.selectedRows.secondaryContactNumber;
    this.staffEmail = this.selectedRows.emailId;
    this.staffAddress = this.selectedRows.address;
    this.staffProfileURL = this.selectedRows.staffName.split(" ").map((n:any)=>n[0]).join(".");
    this.staffWAnum = this.selectedRows.whatsappNumber;
    
    this.httpclient.get('http://localhost:8080/teacherMedicalDetails/'+this.selectedRows.staffId).subscribe((res)=>{
      this.medicalData = res;
    },(err)=>{
      console.log(err);
    })

    this.httpclient.get('http://localhost:8080/teacherFamilyFinancialDetails/'+this.selectedRows.staffId).subscribe((res)=>{
      this.financialData = res;
    },(err)=>{
      console.log(err);
    })

  }
}
