import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatMenuModule } from '@angular/material/menu';
import { Router } from '@angular/router';
import { AgGridAngular, ICellRendererAngularComp } from 'ag-grid-angular';

import {
  ColDef,
  GridApi,
  ICellRendererParams,
  StatusPanelDef,
  GridOptions
} from 'ag-grid-community';
import { DeleteComponent } from './delete/delete.component';
import { read, utils } from 'xlsx';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  teachersCount = 0;
  studentsCount = 0;
  classCount = 0;

  schoolBackgroundImage = 'path/to/school-background-image.jpg';
  searchQuery!: string;
  gridOptions!: GridOptions;

  public rowData: any;
  public staffSearch: any;
  public isRowSelectable: any = (params: any) => {
    return !params.data;
  };
  public columnDefs: ColDef[] = [
    {
      headerName: 'S.No',
      field: 'sno',
      maxWidth: 150,
      valueGetter: 'node.rowIndex + 1',
    },
    {
      headerName: 'Staff Name',
      maxWidth: 150,
      field: 'staffName',
      resizable: true,
      filter: 'agTextColumnFilter',
    },
    {
      headerName: 'Primary Contact',
      maxWidth: 150,
      field: 'primaryContactNumber',
      resizable: true,
      filter: 'agTextColumnFilter',
    },
    {
      headerName: 'Secondary Contact',
      field: 'secondaryContactNumber',
      resizable: true,
      filter: 'agTextColumnFilter',
    },
    {
      headerName: 'Address',
      field: 'address',
      resizable: true,
      filter: 'agTextColumnFilter',
    },
    {
      headerName: 'Email',
      maxWidth: 150,
      field: 'emailId',
      resizable: true,
      filter: 'agTextColumnFilter',
    },
    {
      headerName: 'Whatsapp No',
      maxWidth: 150,
      field: 'whatsappNumber',
      resizable: true,
      cellRenderer: (params: any) => {
        return `<a href="https://wa.me/${params.data.whatsappNumber}" target="_blank">${params.data.whatsappNumber}</a>`;
      },
    },
    { headerName: 'Actions', cellRenderer: ActionCellRendererComponent },
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
  public statusBar: {
    statusPanels: StatusPanelDef[];
  } = {
    statusPanels: [
      { statusPanel: 'agTotalAndFilteredRowCountComponent', align: 'left' },
      { statusPanel: 'agTotalRowCountComponent', align: 'center' },
      { statusPanel: 'agFilteredRowCountComponent' },
      { statusPanel: 'agSelectedRowCountComponent' },
      { statusPanel: 'agAggregationComponent' },
    ],
  };
  gridApi: any;
  onType() {
    const filterValue = this.staffSearch.toLowerCase(); // Convert search value to lowercase for case-insensitive search
    this.gridApi.setQuickFilter(filterValue);
  }
  csvImport(event: any){
    const files = event.target.files;
    console.log("files:", files)
    if(files.length){
      const file = files[0];
      console.log("file:", file);
      const reader = new FileReader();
      console.log(reader);
      reader.onload = (eve : any) => {
        console.log(eve);
        const wb = read(eve.target.result);
        //console.log("wb:", wb);
        const sheets = wb.SheetNames;
        console.log("sheets:", sheets);

        if(sheets.length){
          const rows = utils.sheet_to_json(wb.Sheets[sheets[0]]);
          console.log("rows:...", rows);
          this.rowData = rows;
        }
      };
      reader.readAsArrayBuffer(file); 
    }
  }
  fetchTeachers() {
    this.httpclient
      .get('http://localhost:8080/teacherDetails')
      .subscribe((res:any) => {
    this.teachersCount = res.length;

        this.rowData = res;
      });
  }
  ngOnInit() {
    this.httpclient
      .get('http://localhost:8080/classes/getAll')
      .subscribe((res: any) => {
        this.classCount = res.length;
      });
    this.httpclient
      .get('http://localhost:8080/students/getAll')
      .subscribe((res: any) => {
        this.studentsCount = res.length;
      });
    this.fetchTeachers();
  }
  // onFileSelected(event: any){
  //   const file : File = event.target.files[0];
  //   if(file){
  //     this.parseCSV(file);
  //   }
  // }

  onFileChange(event: any) {
    const file = event.target.files[0];
  }

  uploadCSV() {
    const fileInput = document.querySelector('input[type="file"]');
  }
  onGridReady(param: any) {
    this.gridApi = param.api;
  }

  onSelectionChanged(event: any) {
    selectedRows = event.data;
    console.log(selectedRows);
  }

  constructor(private httpclient: HttpClient) {}
}
var selectedRows: any;
// ActionCellRendererComponent - Custom component to render the action column
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
export class ActionCellRendererComponent implements ICellRendererAngularComp {
  constructor(
    private router: Router,
    private dialog: MatDialog,
    private homeComponent: HomeComponent
  ) {}
  agInit(params: ICellRendererParams<any, any, any>): void {}
  refresh(params: ICellRendererParams<any, any, any>): boolean {
    return true;
  }
  // Implement the edit and delete functions
  edit() {
    this.router.navigate(['/edit-staff'], { state: { rows: selectedRows } });
    // ...
  }

  delete() {
    const dialogRef = this.dialog.open(DeleteComponent, {
      data: { message: '', rows: selectedRows, del: 'teacher' },
    });
    dialogRef.afterClosed().subscribe((res) => {
      this.homeComponent.fetchTeachers();
    });
    // ...
  }

  view() {
    this.router.navigate(['/view-staff'], { state: { rows: selectedRows } });
    // ...
  }
}
