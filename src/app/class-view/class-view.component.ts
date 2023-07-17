import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ColDef, ICellRendererParams } from 'ag-grid-community';
import { DeleteComponent } from '../home/delete/delete.component';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-class-view',
  templateUrl: './class-view.component.html',
  styleUrls: ['./class-view.component.scss'],
})
export class ClassViewComponent {
  username: string = '';
  schoolBackgroundImage = 'path/to/school-background-image.jpg';
  searchQuery!: string;
  public rowData: any;
  public isRowSelectable: any = (params: any) => {
    return !params.data;
  };

  public columnDefs: ColDef[] = [
    {
      headerName: 'S.No',
      field: 'sno',
      width: 200,
      valueGetter: 'node.rowIndex + 1',
    },
    {
      headerName: 'Class Id',
      width: 200,
      field: 'classIdentifier',
      resizable: true,
    },
    {
      headerName: 'Grade',
      width: 200,
      field: 'gradeText',
      resizable: true,
    },
    {
      headerName: 'Division',
      width: 250,
      field: 'divisionText',
      resizable: true,
    },
    {
      headerName: 'Teacher Name',
      width: 300,
      field: 'staffIdentifier',
      resizable: true,
    },
    { headerName: 'Actions', cellRenderer: ActionCellRendererComponent1 },
  ];
  public defaultColDef: ColDef = {
    flex: 1,
    filter: 'agTextColumnFilter',
    menuTabs: ['filterMenuTab'],
  };
  frameworkComponents: any = {
    actionCellRenderer: ActionCellRendererComponent1,
  };
  gridApi: any;
  teachers: any;

  onGridReady(param: any) {
    this.gridApi = param.api;
    this.gridApi.sizeColumnsToFit();
  }
  onSelectionChanged(event: any) {
    this.selectedRows = event.data;
    console.log(this.selectedRows);
  }

  fetchClasses() {
    this.httpclient
      .get('http://localhost:8080/classes/getAll')
      .subscribe((res) => {
        this.rowData = res;
        console.log(res);
        console.log('this is row data:', this.rowData);
        this.rowData.forEach((element: any) => {
          element.staffIdentifier =
            this.teachers.find(
              (teacher: any) => teacher.staffId == element.staffIdentifier
            ).staffName || '';
        });
      });
  }
  constructor(private httpclient: HttpClient) {
    this.username = sessionStorage.getItem('username')!;
    console.log(this.username);

    // Initialize the rowData with your data
    this.rowData = [];
  }
  ngOnInit(): void {
    this.httpclient
      .get('http://localhost:8080/teacherDetails')
      .subscribe((res: any, i = 0) => {
        res.forEach((e: any) => {
          console.log('response of teacherDetails', res);
          this.teachers = res;
          console.log(res);
        });
      });
    this.fetchClasses();
  }

  selectedRows: any;
}
var selectedRows:any;
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
export class ActionCellRendererComponent1 implements ICellRendererAngularComp {
  
  constructor(private router:Router,private dialog:MatDialog,private classComponent:ClassViewComponent){

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
      data: {message:'',rows:selectedRows,del:'class'},
    });
    dialogRef.afterClosed().subscribe((res)=>{
      this.classComponent.fetchClasses()
    })
    // ...
  }
  
  view(){
    this.router.navigate(['/view-staff'],{state:{rows:selectedRows}})
    // ...
  }
}
