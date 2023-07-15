import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ColDef, ICellRendererParams } from 'ag-grid-community';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  schoolBackgroundImage = 'path/to/school-background-image.jpg';
  searchQuery!: string;
  public rowData: any;
  public columnDefs: ColDef[] = [
    { headerName: 'S.No', field: 'sno', maxWidth: 150, valueGetter: "node.rowIndex + 1" },
    { headerName: 'Staff Name', maxWidth: 150, field: 'staffName',resizable:true },
    { headerName: 'Primary Contact', maxWidth: 150  , field: 'primaryContactNumber',resizable:true },
    { headerName: 'Secondary Contact', field: 'secondaryContactNumber',resizable:true },
    { headerName: 'Address', field: 'address',resizable:true },
    { headerName: 'Email', maxWidth: 150,field: 'emailId',resizable:true },
    { headerName: 'Whatsapp No', maxWidth: 150, field: 'whatsappNumber',resizable:true },
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

  fetchTeachers(){
    this.httpclient.get('http://localhost:8080/teacherDetails').subscribe((res)=>{
      this.rowData = res;
      console.log(res);
      
    })
  }
  ngOnInit(){
    this.fetchTeachers()
  }

  onGridReady(param: any) {
    this.gridApi = param.api;
    this.gridApi.sizeColumnsToFit();
    
  }
  constructor(private httpclient:HttpClient) {
    // Initialize the rowData with your data
   
  }
}

// ActionCellRendererComponent - Custom component to render the action column
@Component({
  template: `
   <button mat-button [matMenuTriggerFor]="menu"><mat-icon>more_vert</mat-icon></button>
        <mat-menu #menu="matMenu">
        <button mat-menu-item (click)="view">View</button>
          <button mat-menu-item (click)="edit">Edit</button>
          <button mat-menu-item (click)="delete">Delete</button>
        </mat-menu>
  `,
})
export class ActionCellRendererComponent implements ICellRendererAngularComp {
  agInit(params: ICellRendererParams<any, any, any>): void {
   
  }
  refresh(params: ICellRendererParams<any, any, any>): boolean {
    return true
  }
  // Implement the edit and delete functions
  edit() {
    // ...
  }

  delete() {
    // ...
  }
  
  view(){
    // ...
  }
}
