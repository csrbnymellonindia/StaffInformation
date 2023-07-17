import { Component, OnInit } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ColDef, ICellRendererParams } from 'ag-grid-community';
import { DeleteComponent } from '../home/delete/delete.component';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-teacher-home',
  templateUrl: './teacher-home.component.html',
  styleUrls: ['./teacher-home.component.scss'],
})
export class TeacherHomeComponent implements OnInit {
  username: string = '';
  schoolBackgroundImage = 'path/to/school-background-image.jpg';
  searchQuery!: string;
  public isRowSelectable: any = (params: any) => {
    return !params.data;
  };
  public rowData: any;
  public columnDefs: ColDef[] = [
    {
      headerName: 'S.No',
      field: 'sno',
      maxWidth: 150,
      valueGetter: 'node.rowIndex + 1',
    },
    {
      headerName: 'First Name',
      maxWidth: 150,
      field: 'studentFirstName',
      resizable: true,
    },
    {
      headerName: 'Last Name',
      maxWidth: 150,
      field: 'studentLastName',
      resizable: true,
    },
    {
      headerName: 'Primary Contact',
      maxWidth: 150,
      field: 'primaryContactNumber',
      resizable: true,
    },
    { headerName: 'Address', field: 'currentAddressLine1', resizable: true },
    {
      headerName: 'Email',
      maxWidth: 150,
      field: 'studentEmailAddress',
      resizable: true,
    },
    {
      headerName: 'Gender',
      maxWidth: 150,
      field: 'studentGender',
      resizable: true,
    },
    {
      headerName: 'DOB',
      field:'studentBirthDayNumber',
      maxWidth: 150,
      resizable: true,
      cellRenderer: 'agGroupCellRenderer',
      cellRendererParams: {
        innerRenderer: (params: any) => {
          console.log(params.data);
          
          return (
            `${params.data.studentBirthDayNumber.toString() + '/'+
            params.data.studentBirthMonthNumber.toString() + '/' +
            params.data.studentBirthYear}`
          );
        },
      },
    },
    { headerName: 'Actions', cellRenderer: ActionCellRendererComponent2 },
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
  onSelectionChanged(event: any) {
    selectedRows = event.data;
    console.log(selectedRows);
  }
  fetchStudents() {
    this.httpclient
      .get('http://localhost:8080/students/getAll')
      .subscribe((res) => {
        this.rowData = res;
        console.log(res);
      });
  }
  constructor(private httpclient: HttpClient) {
    this.username = sessionStorage.getItem('username')!;
    console.log(this.username);

    // Initialize the rowData with your data
    this.rowData = [];
  }
  ngOnInit(): void {
    this.fetchStudents();
  }
}
var selectedRows: any;
@Component({
  template: `
    <button mat-button [matMenuTriggerFor]="menu">
      <mat-icon>more_vert</mat-icon>
    </button>
    <mat-menu #menu="matMenu">
      <button mat-menu-item (click)="view()">View</button>
      <button mat-menu-item (click)="edit()">Edit</button>
      <button mat-menu-item (click)="delete()">Delete</button>
    </mat-menu>
  `,
})
export class ActionCellRendererComponent2 implements ICellRendererAngularComp {
  constructor(
    private router: Router,
    private dialog: MatDialog,
    private teachercomponent: TeacherHomeComponent
  ) {}
  agInit(params: ICellRendererParams<any, any, any>): void {}
  refresh(params: ICellRendererParams<any, any, any>): boolean {
    return true;
  }
  // Implement the edit and delete functions
  edit() {
    this.router.navigate(['/edit-student'], { state: { rows: selectedRows } });
    // ...
  }

  delete() {
    const dialogRef = this.dialog.open(DeleteComponent, {
      data: { message: '', rows: selectedRows },
    });
    dialogRef.afterClosed().subscribe((res: any) => {
      this.teachercomponent.fetchStudents();
    });
    // ...
  }

  view() {
    this.router.navigate(['/view-student'], { state: { rows: selectedRows } });
    // ...
  }
}
