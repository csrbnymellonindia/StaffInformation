import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ColDef, ICellRendererParams } from 'ag-grid-community';
import { DeleteComponent } from '../home/delete/delete.component';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { HomeComponent } from '../home/home.component';
@Component({
  selector: 'app-teacher-home',
  templateUrl: './teacher-home.component.html',
  styleUrls: ['./teacher-home.component.scss']
})
export class TeacherHomeComponent {
  username:string='';
  schoolBackgroundImage = 'path/to/school-background-image.jpg';
  searchQuery!: string;
  public isRowSelectable:any=(params:any)=>{
    return !params.data;
  }
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
    { headerName:'Actions', cellRenderer:ActionCellRendererComponent2}
    // { headerName: 'Action', field: 'action', cellRenderer: 'actionCellRenderer' }
  ];
  public defaultColDef: ColDef = {
    flex: 1,
    filter: 'agTextColumnFilter',
    menuTabs: ['filterMenuTab'],
  };
  frameworkComponents: any = {
    actionCellRenderer: ActionCellRendererComponent2,
  };
  gridApi: any;

  onGridReady(param: any) {
    this.gridApi = param.api;
    this.gridApi.sizeColumnsToFit();
  }
  onSelectionChanged(event:any){
    selectedRows = event.data;
    console.log(selectedRows);
    
  }
  constructor() {
    this.username = sessionStorage.getItem('username')!;
    console.log(this.username);
    
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
var selectedRows: any;
@Component({
  template: `
   <button mat-button [matMenuTriggerFor]="menu"><mat-icon>more_vert</mat-icon></button>
        <mat-menu #menu="matMenu">
        <button mat-menu-item (click)="view()">View</button>
          <button mat-menu-item (click)="edit()">Edit</button>
          <button mat-menu-item (click)="delete()">Delete</button>
        </mat-menu>
  `,
})
export class ActionCellRendererComponent2 implements ICellRendererAngularComp {
  
  constructor(private router:Router,private dialog:MatDialog,private homeComponent:HomeComponent){

  }
  agInit(params: ICellRendererParams<any, any, any>): void {
   
  }
  refresh(params: ICellRendererParams<any, any, any>): boolean {
    return true
  }
  // Implement the edit and delete functions
  edit() {
    this.router.navigate(['/edit-staff'],{state:{rows:selectedRows}})
    // ...
  }

  delete() {
    const dialogRef = this.dialog.open(DeleteComponent, {
      data: {message:'',rows:selectedRows},
    });
    dialogRef.afterClosed().subscribe((res: any)=>{
      this.homeComponent.fetchTeachers()
    })
    // ...
  }
  
  view(){
    this.router.navigate(['/view-staff'],{state:{rows:selectedRows}})
    // ...
  }
}
