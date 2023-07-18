import { Component } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SuccessdialogComponent } from '../successdialog/successdialog.component';
import { AuthService } from '../services/auth.service';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-classroom',
  templateUrl: './add-classroom.component.html',
  styleUrls: ['./add-classroom.component.scss']
})
export class AddClassroomComponent {
  myForm!: FormGroup;
  teachers: string[] = [];
  obj : any = [];
  selectedStaffId : any;
  selectedTeacher!: string;
  constructor(
    private formBuilder : FormBuilder,
    private httpclient:HttpClient,
    private authService:AuthService,
    private dialog:MatDialog,
    private router:Router){}

    ngOnInit(){
      this.httpclient.get('http://localhost:8080/teacherDetails').subscribe((res : any, i = 0) => {
        res.forEach((e: any) => {
          this.teachers.push(e.staffName);
        })
      })
      this.myForm = this.formBuilder.group({
        classId: ['', Validators.required],
        grade: ['', Validators.required],
        division: ['', Validators.required],
        teacherName: ['', Validators.required],
      });
    }

    onSubmit(){
      if(this.myForm.valid){
        const formData = this.myForm.value;
       //console.log(formData);
       this.selectedTeacher = formData.teacherName;
       console.log(this.selectedTeacher);
       this.httpclient.get('http://localhost:8080/teacherDetails').subscribe((res: any) => {
        res.forEach((e: any) => {
          if(e.staffName === this.selectedTeacher){
            this.selectedStaffId = e.staffId;
            this.obj.push({
              "classIdentifier" : formData.classId,
              "gradeText" : formData.grade,
              "divisionText" : formData.division,
              "staffIdentifier" : this.selectedStaffId
            })
            console.log(this.obj);
            this.httpclient.post('http://localhost:8080/classes/addClass', this.obj[0], {observe: 'response',responseType:'text'}).subscribe((res: HttpResponse<any>) => {
              if(res.status === 200){
                this.dialog.open(SuccessdialogComponent,{disableClose:true,data:{message:'Added a Class'}});
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
          }else {
            this.markFormGroupTouched(this.myForm);
          }
        })
        
       })
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
