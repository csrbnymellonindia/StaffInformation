import { Component } from '@angular/core';

@Component({
  selector: 'app-view-staff',
  templateUrl: './view-staff.component.html',
  styleUrls: ['./view-staff.component.scss']
})
export class ViewStaffComponent {
  staffName: String;
  primaryContactNum: String;
  secondaryContactNum: String;
  staffEmail: String;
  staffWAnum: String;
  staffAddress: String;
  staffProfileURL: String;

  constructor(){
    this.staffName = "Richa Sharma";
    this.primaryContactNum = "1234567890";
    this.secondaryContactNum = "1234567890";
    this.staffEmail = "abc@gmail.com";
    this.staffWAnum = "1234567890";
    this.staffAddress = "address of staff";
    this.staffProfileURL = "../../assets/images/user.png";
  }
}
