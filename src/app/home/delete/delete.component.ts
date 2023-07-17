import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-delete',
  template: `<h3 mat-dialog-title style="color: green;">Delete {{data.rows.staffName}}?</h3>
  <div mat-dialog-content>
    The record will be deleted!
  </div>
  <div mat-dialog-actions>
  <button mat-button (click)="onNoClick()">Cancel</button>
  <button mat-button mat-dialog-close="" (click)="onClick()" cdkFocusInitial>Delete</button>
</div>`,
  styleUrls: ['./delete.component.scss']
})
export class DeleteComponent {

  onClick(){
    this.httpclient.delete('http://localhost:8080/deleteTeacher/'+this.data.rows.staffId).subscribe((res)=>{
    
    },(err)=>{
      console.log(err);
      
    })
  }

  onNoClick(){

  }
  constructor(@Inject(MAT_DIALOG_DATA) public data: { message: string,rows:any },private httpclient:HttpClient) {}
}
