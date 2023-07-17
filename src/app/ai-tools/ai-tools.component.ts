import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { SuccessdialogComponent } from '../successdialog/successdialog.component';

@Component({
  selector: 'app-ai-tools',
  templateUrl: './ai-tools.component.html',
  styleUrls: ['./ai-tools.component.scss']
})
export class AiToolsComponent {
  myForm!: FormGroup;
  teachers: any;
  obj : any = [];
  selectedStaffId : any;
  selectedTeacher!: string;
  feedbacks: any;
  generatedContent: string = '';
  teachersList: string[] = [];
  loading = false;
  constructor(
    private formBuilder : FormBuilder,
    private httpclient:HttpClient,
    private authService:AuthService,
    private dialog:MatDialog,
    private router:Router){}

    ngOnInit(){
      this.generatedContent = 'Select teacher and click generate';
      this.httpclient.get('http://localhost:8080/teacherDetails').subscribe((res: any) => {
          this.teachers = res;
          res.forEach((e: any) => {
            this.teachersList.push(e.staffName);
          })
          
      })
      this.httpclient.get('http://localhost:8080/feedbacks/getAll').subscribe((res: any) => {
        this.feedbacks = res;
        console.log(res);
        
    })
    
    
    
      this.myForm = this.formBuilder.group({
        teacherName: ['', Validators.required],
      });
    }

    onSubmit(){
      if(this.myForm.valid){
        const formData = this.myForm.value;
       //console.log(formData);
       this.selectedTeacher = formData.teacherName;

       const selectedfeedbacks = this.feedbacks.filter((e:any)=>{
        return e.userIdentifier == parseInt(formData.teacherName)
       })

       console.log(selectedfeedbacks);
       let prompt = "Hi I am teacher at Little flower school of deaf, here are my students feedback based on this give me recommendation to improve,";
        selectedfeedbacks.forEach((element: any,index:number) => {
          if(element.feedbackDescription!=''){
          prompt += (index+1).toString()+'.'+element.feedbackDescription+' ';
          }
      });
      console.log(prompt);
      const payload = {
        "text": prompt
      }
      this.loading = true;
      this.httpclient.post('http://localhost:8080/generateText',payload).subscribe((res:any)=>{
        this.loading = false;
        this.generatedContent = res.openai.generated_text;
      },(err)=>{
        console.log(err);
        
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
