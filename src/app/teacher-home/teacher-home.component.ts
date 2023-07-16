import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ColDef, ICellRendererParams } from 'ag-grid-community';
import { ActionCellRendererComponent } from '../home/home.component';
@Component({
  selector: 'app-teacher-home',
  templateUrl: './teacher-home.component.html',
  styleUrls: ['./teacher-home.component.scss']
})
export class TeacherHomeComponent {
  schoolBackgroundImage = 'path/to/school-background-image.jpg';
  searchQuery!: string;
  public rowData: any;
  public columnDefs: ColDef[] = [
    { headerName: 'S.No', field: 'sno', maxWidth: 150,  },
    { headerName: 'First Name', maxWidth: 150, field: 'firstName',resizable:true },
    { headerName: 'Last Name', maxWidth: 150, field: 'lastName',resizable:true },
    { headerName: 'Primary Contact', maxWidth: 150  , field: 'primaryContact',resizable:true },
    { headerName: 'Address', field: 'address',resizable:true },
    { headerName: 'Email', maxWidth: 150,field: 'email',resizable:true },
    { headerName: 'Gender', maxWidth: 150,field: 'gender',resizable:true },
    { headerName: 'DOB', maxWidth: 150,field: 'dob',resizable:true },
    { headerName:'Actions', cellRenderer:ActionCellRendererComponent}
    // { headerName: 'Action', field: 'action', cellRenderer: 'actionCellRenderer' }
  ];
  public defaultColDef: ColDef = {
    flex: 1,
    filter: 'agTextColumnFilter',
    menuTabs: ['filterMenuTab'],
  };
  frameworkComponents: any = {
    actionCellRenderer: ActionCellRendererComponent,
  };
  gridApi: any;

  onGridReady(param: any) {
    this.gridApi = param.api;
    this.gridApi.sizeColumnsToFit();
  }
  constructor() {
    // Initialize the rowData with your data
    this.rowData = [
      {
        sno: 1,
        firstName: 'Celica',
        lasttName: 'Celica',
        primaryContact: 7305746710,
        address: 'No.5',
        email: 'asda@asd.com',
        gender: 'Male',
        dob: '13-6-2004',
        action: '',
      },
      {
        sno: 2,
        firstName: 'Celica',
        lasttName: 'Celica',
        primaryContact: 7305746710,
        address: 'No.5',
        email: 'asda@asd.com',
        gender: 'Male',
        dob: '13-6-2004',
        action: '',
      },
    ];
  }
}
