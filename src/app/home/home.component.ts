import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatMenuModule } from '@angular/material/menu'; 
import { Router } from '@angular/router';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ColDef, ICellRendererParams } from 'ag-grid-community';
import { DeleteComponent } from './delete/delete.component';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  schoolBackgroundImage = 'path/to/school-background-image.jpg';
  searchQuery!: string;
  public rowData: any;
  public isRowSelectable:any=(params:any)=>{
    return !params.data;
  }
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
    })
  }
  ngOnInit(){
    this.fetchTeachers()
  }

  onGridReady(param: any) {
    this.gridApi = param.api;
    this.gridApi.sizeColumnsToFit();
    
  }

  onSelectionChanged(event:any){
    selectedRows = event.data;
    console.log(selectedRows);
    
  }

  constructor(private httpclient:HttpClient) {
    // Initialize the rowData with your data
   
  }
}
var selectedRows: any;
// ActionCellRendererComponent - Custom component to render the action column
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
export class ActionCellRendererComponent implements ICellRendererAngularComp {
  
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
    dialogRef.afterClosed().subscribe((res)=>{
      this.homeComponent.fetchTeachers()
    })
    // ...
  }
  
  view(){
    this.router.navigate(['/view-staff'],{state:{rows:selectedRows}})
    // ...
  }
}
