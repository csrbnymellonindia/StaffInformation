import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import {
  CheckboxControlValueAccessor,
  FormBuilder,
  FormGroup,
  NG_VALUE_ACCESSOR,
  Validators,
} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from '../services/auth.service';
import { SuccessdialogComponent } from '../successdialog/successdialog.component';

@Component({
  selector: 'app-edit-student',
  templateUrl: './edit-student.component.html',
  styleUrls: ['./edit-student.component.scss'],
})
export class EditStudentComponent implements OnInit {
  generalInfoForm!: FormGroup;
  medicalInfoForm!: FormGroup;
  financialAadhaarForm!: FormGroup;
  shortLink: string = '';
  loading: boolean = false;
  selectedFile!: File | null;
  datePipe: DatePipe;
  selectedRows: any;
  updateCheckboxValue(controlName: string) {
    const control = this.generalInfoForm.get(controlName)!;
    control.setValue(!control.value);
  }
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private httpclient: HttpClient,
    private dialog: MatDialog,
    private authService: AuthService
  ) {
    this.datePipe = new DatePipe('en-US');
    this.selectedRows =
      this.router.getCurrentNavigation()?.extras.state?.['rows'];
  }

  ngOnInit() {
    this.generalInfoForm = this.formBuilder.group({
      studentFirstName: [this.selectedRows.studentFirstName],
      studentIdentifier: [this.selectedRows.studentIdentifier],
      studentMiddleName: [this.selectedRows.studentMiddleName],
      studentLastName: [this.selectedRows.studentLastName],
      studentEmailAddress: [this.selectedRows.studentEmailAddress],
      studentContactNumber: [this.selectedRows.studentContactNumber],
      studentBirthDate: [this.selectedRows.studentBirthDate],
      studentBirthMonthNumber: [this.selectedRows.studentBirthMonthNumber],
      studentBirthDayNumber: [this.selectedRows.studentBirthDayNumber],
      studentBirthYear: [this.selectedRows.studentBirthYear],
      studentGender: [this.selectedRows.studentGender],
      admittedGrade: [this.selectedRows.admittedGrade],
      admittedDivision: [this.selectedRows.admittedDivision],
      admissionDate: [this.selectedRows.admissionDate],
      admissionMonthNumber: [this.selectedRows.admissionMonthNumber],
      admissionDayNumber: [this.selectedRows.admissionDayNumber],
      admissionYear: [this.selectedRows.admissionYear],
      disabilityIndicator: [this.selectedRows.disabilityIndicator],
      fatherName: [this.selectedRows.fatherName],
      fatherOccupation: [this.selectedRows.fatherOccupation],
      fatherAnnualIncome: [this.selectedRows.fatherAnnualIncome],
      motherName: [this.selectedRows.motherName],
      motherOccupation: [this.selectedRows.motherOccupation],
      motherAnnualIncome: [this.selectedRows.motherAnnualIncome],
      studentAadharIdentifier: [this.selectedRows.studentAadharIdentifier],
      currentAddressLine1: [this.selectedRows.currentAddressLine1],
      currentAddressLine2: [this.selectedRows.currentAddressLine2],
      currentAddressLine3: [this.selectedRows.currentAddressLine3],
      currentAddressCity: [this.selectedRows.currentAddressCity],
      currentAddressState: [this.selectedRows.currentAddressState],
      currentAddressZipCode: [this.selectedRows.currentAddressZipCode],
      permanentAddressLine1: [this.selectedRows.permanentAddressLine1],
      permanentAddressLine2: [this.selectedRows.permanentAddressLine2],
      permanentAddressLine3: [this.selectedRows.permanentAddressLine3],
      permanentAddressCity: [this.selectedRows.permanentAddressCity],
      permanentAddressState: [this.selectedRows.permanentAddressState],
      permanentAddressZipCode: [this.selectedRows.permanentAddressZipCode],
      primaryContactName: [this.selectedRows.primaryContactName],
      primaryContactNumber: [this.selectedRows.primaryContactNumber],
      primaryContactRelationship: [
        this.selectedRows.primaryContactRelationship,
      ],
      secondaryContactName: [this.selectedRows.secondaryContactName],
      secondaryContactNumber: [this.selectedRows.secondaryContactNumber],
      secondaryContactRelationship: [
        this.selectedRows.secondaryContactRelationship,
      ],
      physicianName: [this.selectedRows.physicianName],
      physicianPrimaryContactNumber: [
        this.selectedRows.physicianPrimaryContactNumber,
      ],
      physicianSecondaryContactNumber: [
        this.selectedRows.physicianSecondaryContactNumber,
      ],
      preferredEmergencyHospital: [
        this.selectedRows.preferredEmergencyHospital,
      ],
      currentMedicationDetail: [this.selectedRows.currentMedicationDetail],
      medicalAllergyDetail: [this.selectedRows.medicalAllergyDetail],
      foodAllergyDetail: [this.selectedRows.foodAllergyDetail],
      chronicHealthIssueDetail: [this.selectedRows.chronicHealthIssueDetail],
      previousSchoolName: [this.selectedRows.previousSchoolName],
      previousSchoolCity: [this.selectedRows.previousSchoolCity],
      previousSchoolState: [this.selectedRows.previousSchoolState],
      previousSchoolAdmissionDate: [
        this.selectedRows.previousSchoolAdmissionDate,
      ],
      previousSchoolLastDate: [this.selectedRows.previousSchoolLastDate],
      additionalDetails: [this.selectedRows.additionalDetails],
      whatsappNumber: [this.selectedRows.whatsappNumber],
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
  onSubmit() {
    const dateOfBirthValue =
      this.generalInfoForm.get('studentBirthDate')?.value;
    const dateOfAdmission = this.generalInfoForm.get('admissionDate')?.value;
    const studentIdentifier = parseInt(
      this.generalInfoForm.get('studentIdentifier')?.value
    );
    const motherAnnualIncome = parseInt(
      this.generalInfoForm.get('motherAnnualIncome')?.value
    );
    const fatherAnnualIncome = parseInt(
      this.generalInfoForm.get('fatherAnnualIncome')?.value
    );
    // Extract the day, month, and year components from the date
    const birthDate = new Date(dateOfBirthValue);
    const studentBirthDayNumber = birthDate.getDate();
    const studentBirthMonthNumber = birthDate.getMonth() + 1; // Add 1 since months are zero-based
    const studentBirthYear = birthDate.getFullYear().toString();

    const admissionDate = new Date(dateOfAdmission);
    const admissionDayNumber = admissionDate.getDate();
    const admissionMonthNumber = admissionDate.getMonth() + 1; // Add 1 since months are zero-based
    const admissionYear = admissionDate.getFullYear().toString();

    const previousSchoolAdmissionDate = this.formatDate(
      this.generalInfoForm.get('previousSchoolAdmissionDate')?.value
    );
    const previousSchoolLastDate = this.formatDate(
      this.generalInfoForm.get('previousSchoolLastDate')?.value
    );

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
    });
    const studentData = {
      ...this.generalInfoForm.value,
    };
    console.log(studentData);

    this.httpclient
      .put('http://localhost:8080/students/updateStud/'+this.selectedRows.studentIdentifier, studentData,{observe:'response',responseType:'text'})
      .subscribe(
        (res) => {
          if(res.status==200){
            this.dialog.open(SuccessdialogComponent,{disableClose:true,data:{message:'Edited Student'}});
            setTimeout(()=>{
              this.dialog.closeAll()
              this.authService.isLoggedVar.next(true);
            this.router.navigate(['/student-view'])
            },2000)
          }else{
            this.generalInfoForm.setErrors({serverError:true})
          }
        },
        (err) => {
          console.log(err);
        }
      );
  }
  onFileChange(event: any) {
    this.selectedFile = event.target.files[0];
  }
}
