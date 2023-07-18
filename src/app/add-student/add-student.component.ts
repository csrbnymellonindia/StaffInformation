import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { CheckboxControlValueAccessor, FormBuilder, FormGroup, NG_VALUE_ACCESSOR, Validators } from '@angular/forms';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { SuccessdialogComponent } from '../successdialog/successdialog.component';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from '../services/auth.service';


@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.scss']
})
export class AddStudentComponent implements OnInit {
  generalInfoForm!: FormGroup;
  medicalInfoForm!: FormGroup;
  addlDetails : string | null = null;
  financialAadhaarForm!: FormGroup;
  shortLink : string = "";
  loading: boolean = false;
  file: File | null = null;
  selectedFile!: File | null;
  datePipe: DatePipe;
  updateCheckboxValue(controlName: string) {
    const control = this.generalInfoForm.get(controlName)!;
    control.setValue(!control.value);
  }
  constructor(private formBuilder: FormBuilder,private router:Router,private httpclient:HttpClient,private dialog:MatDialog,private authService:AuthService) {
  this.datePipe = new DatePipe('en-US');

  }

  ngOnInit() {
  this.generalInfoForm = this.formBuilder.group({
    studentFirstName: [''],
    studentIdentifier: [''],
    studentMiddleName: [''],
    studentLastName: [''],
    studentEmailAddress: [''],
    studentContactNumber: [''],
    studentBirthDate: [''],
    studentBirthMonthNumber: [''],
    studentBirthDayNumber: [''],
    studentBirthYear: [''],
    studentGender: [''],
    admittedGrade:[''],
    admittedDivision:[''],
    admissionDate: [''],
    admissionMonthNumber: [''],
    admissionDayNumber: [''],
    admissionYear: [''],
    disabilityIndicator: [null],
    fatherName: [''],
    fatherOccupation: [''],
    fatherAnnualIncome: [null],
    motherName: [''],
    motherOccupation: [''],
    motherAnnualIncome: [null],
    studentAadharIdentifier: [''],
    currentAddressLine1: [''],
    currentAddressLine2: [''],
    currentAddressLine3: [null],
    currentAddressCity: [''],
    currentAddressState: [''],
    currentAddressZipCode: [''],
    permanentAddressLine1: [''],
    permanentAddressLine2: [null],
    permanentAddressLine3: [null],
    permanentAddressCity: [''],
    permanentAddressState: [''],
    permanentAddressZipCode: [''],
    primaryContactName: [''],
    primaryContactNumber: [''],
    primaryContactRelationship: [''],
    secondaryContactName: [null],
    secondaryContactNumber: [null],
    secondaryContactRelationship: [null],
    physicianName: [''],
    physicianPrimaryContactNumber: [''],
    physicianSecondaryContactNumber: [null],
    preferredEmergencyHospital: [''],
    currentMedicationDetail: [null],
    medicalAllergyDetail: [null],
    foodAllergyDetail: [null],
    chronicHealthIssueDetail: [null],
    previousSchoolName: [''],
    previousSchoolCity: [''],
    previousSchoolState: [''],
    previousSchoolAdmissionDate: [null],
    previousSchoolLastDate: [null],
    additionalDetails: [null],
    whatsappNumber: ['']
  });
  }
  goBack() {
    this.router.navigate(['/student-view']);
  }
  private formatDate(date: Date): string {
    if (date) {
      return this.datePipe.transform(date, 'dd/MM/yyyy') || '';
    }
    return '';
  }
  onFileSelected(event : Event) : void{
    const inputElement = event.target as HTMLInputElement;
    this.file = inputElement.files && inputElement.files[0];
  }
  convertToBase64(): Promise<string> { 
    return new Promise<string>((resolve, reject) => {
      const reader = new FileReader();
      reader.onloadend = () => {
        const base64string = reader.result as string;
        resolve(base64string);
      };
      reader.onerror = (error) => reject(error);
      reader.readAsDataURL(this.file as Blob);
    })
  }
  async onSubmit(): Promise<void>{
    if(this.file) {
      const base64string = await this.convertToBase64();
      this.addlDetails = base64string;
      const dateOfBirthValue = this.generalInfoForm.get('studentBirthDate')?.value;
      const dateOfAdmission = this.generalInfoForm.get('admissionDate')?.value;
      const studentIdentifier = parseInt(this.generalInfoForm.get('studentIdentifier')?.value);
      const motherAnnualIncome = parseInt(this.generalInfoForm.get('motherAnnualIncome')?.value);
      const fatherAnnualIncome = parseInt(this.generalInfoForm.get('fatherAnnualIncome')?.value);
      // Extract the day, month, and year components from the date
      const birthDate = new Date(dateOfBirthValue);
      const studentBirthDayNumber = birthDate.getDate();
      const studentBirthMonthNumber = birthDate.getMonth() + 1; // Add 1 since months are zero-based
      const studentBirthYear = birthDate.getFullYear().toString();
      const additionalDetails = this.addlDetails;
      console.log("additional:", additionalDetails);
      const admissionDate = new Date(dateOfAdmission);
      const admissionDayNumber = admissionDate.getDate();
      const admissionMonthNumber = admissionDate.getMonth() + 1; // Add 1 since months are zero-based
      const admissionYear = admissionDate.getFullYear().toString();
      const previousSchoolAdmissionDate = this.formatDate(this.generalInfoForm.get('previousSchoolAdmissionDate')?.value);
      const previousSchoolLastDate = this.formatDate(this.generalInfoForm.get('previousSchoolLastDate')?.value);
      this.generalInfoForm.patchValue({
        studentBirthDayNumber,
        studentBirthMonthNumber,
        studentBirthYear,
        admissionDayNumber,
        admissionMonthNumber,
        admissionYear,
        studentIdentifier,
        motherAnnualIncome,
        fatherAnnualIncome,
        previousSchoolAdmissionDate,
        previousSchoolLastDate,
        additionalDetails
      });
    }


   
    const studentData = {
      ...this.generalInfoForm.value 
    }
    console.log(studentData);
    
    this.httpclient.post('http://localhost:8080/students/addStud',studentData,{observe:'response',responseType:'text'}).subscribe((res:HttpResponse<any>)=>{
      if(res.status==200){
        this.dialog.open(SuccessdialogComponent,{disableClose:true,data:{message:'Added Student'}});
        setTimeout(()=>{
          this.dialog.closeAll()
          this.authService.isLoggedVar.next(true);
        this.router.navigate(['/student-view'])
        },2000)
      }else{
        this.generalInfoForm.setErrors({serverError:true})
      }
      
    },(err)=>{
      console.log(err);
      
    })
  }
  onFileChange(event: any){
    this.selectedFile = event.target.files[0];
  }
}
