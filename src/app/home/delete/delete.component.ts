import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-delete',
  template: `<h3 mat-dialog-title style="color: green;">
      Delete {{ data.rows.staffName }}?
    </h3>
    <div mat-dialog-content>The record will be deleted!</div>
    <div mat-dialog-actions>
      <button mat-button (click)="onNoClick()">Cancel</button>
      <button
        mat-button
        mat-dialog-close=""
        (click)="onClick()"
        cdkFocusInitial
      >
        Delete
      </button>
    </div>`,
  styleUrls: ['./delete.component.scss'],
})
export class DeleteComponent {
  onClick() {
    if (this.data.del == 'teacher') {
      this.httpclient
        .delete('http://localhost:8080/deleteTeacher/' + this.data.rows.staffId)
        .subscribe(
          (res) => {},
          (err) => {
            console.log(err);
          }
        );
    } else if(this.data.del == 'class'){
      this.httpclient.delete('http://localhost:8080/classes/deleteClass/' + this.data.rows.classIdentifier,{observe:'response',responseType:'text'} )
        .subscribe(
          (res) => {},
          (err) => {
            console.log(err);
          }
        );
    } else {
      this.httpclient
        .delete('http://localhost:8080/students/deleteStud/' + this.data.rows.studentIdentifier,{observe:'response',responseType:'text'} )
        .subscribe(
          (res) => {},
          (err) => {
            console.log(err);
          }
        );
    }
  }

  onNoClick() {
    this.dialogRef.close();
  }
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { message: string; rows: any;del:string },
    private httpclient: HttpClient,public dialogRef: MatDialogRef<DeleteComponent>
  ) {}
}
