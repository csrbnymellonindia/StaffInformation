import { Component, Input } from '@angular/core';

@Component({
  selector: 'dialog-data-example-dialog',
  template: `<h3 mat-dialog-title style="color: green;">Successfully {{message}}</h3>
  <div mat-dialog-content>
    Welcome to Teacher Management System!
  </div>`,
})
export class SuccessdialogComponent {
  @Input() message:string = ''
}
