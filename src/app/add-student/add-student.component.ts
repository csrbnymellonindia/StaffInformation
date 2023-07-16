import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.scss']
})
export class AddStudentComponent implements OnInit {
  generalInfoForm!: FormGroup;
  medicalInfoForm!: FormGroup;
  financialAadhaarForm!: FormGroup;
  shortLink : string = "";
  loading: boolean = false;
  selectedFile!: File | null;

  constructor(private formBuilder: FormBuilder,private router:Router) {}

  ngOnInit() {
    this.generalInfoForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      middleName: [''],
      email: ['', Validators.required],
      contactNumber: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      gender: ['', Validators.required],
      admissionDate: ['', Validators.required],
      fatherName: ['', Validators.required],
      motherName: ['', Validators.required],
      currentAddress: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      zipCode: ['', Validators.required],
      primaryContact: ['', Validators.required],
      secondaryContact: ['', Validators.required],
      admissionGrade: ['', Validators.required],
      previousSchoolName: ['', Validators.required],
      whatsappNumber: ['', Validators.required]
    });

    this.medicalInfoForm = this.formBuilder.group({
      physicianName: ['', Validators.required],
      physicianContact: ['', Validators.required],
      emergencyHospital: ['', Validators.required],
      currentMedication: ['', Validators.required],
      medicalAllergy: ['', Validators.required],
      foodAllergy: ['', Validators.required],
      chronicHealthIssue: ['', Validators.required]
    });

    this.financialAadhaarForm = this.formBuilder.group({
      fatherOccupation: ['', Validators.required],
      fatherIncome: ['', Validators.required],
      motherOccupation: ['', Validators.required],
      motherIncome: ['', Validators.required],
      aadhaarNumber: ['', Validators.required]
    });
  }
  goBack() {
    this.router.navigate(['/student-view']);
  }
  onFileChange(event: any){
    this.selectedFile = event.target.files[0];
  }
}
