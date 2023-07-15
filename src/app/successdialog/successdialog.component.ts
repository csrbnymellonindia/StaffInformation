import { Component, Inject, Input } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'dialog-data-example-dialog',
  template: `<h3 mat-dialog-title style="color: green;">Successfully {{data.message}}</h3>
  <div mat-dialog-content>
    Welcome to Teacher Management System!
  </div>`,
})
export class SuccessdialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: { message: string }) {}
}
