import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ColDef, ICellRendererParams } from 'ag-grid-community';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  schoolBackgroundImage = 'path/to/school-background-image.jpg';
  searchQuery!: string;
  public rowData: any;
  public columnDefs: ColDef[] = [
    { headerName: 'S.No', field: 'sno', maxWidth: 150,  },
    { headerName: 'Staff Name', maxWidth: 150, field: 'staffName',resizable:true },
    { headerName: 'Primary Contact', maxWidth: 150  , field: 'primaryContact',resizable:true },
    { headerName: 'Secondary Contact', field: 'secondaryContact',resizable:true },
    { headerName: 'Address', field: 'address',resizable:true },
    { headerName: 'Email', maxWidth: 150,field: 'email',resizable:true },
    { headerName: 'Whatsapp No', maxWidth: 150, field: 'whatsappno',resizable:true },
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
        staffName: 'Celica',
        primaryContact: 7305746710,
        secondaryContact: 12345678,
        address: 'No.5',
        email: 'asda@asd.com',
        whatsappno: '980102',
        action: '',
      },
    ];
  }
}

// ActionCellRendererComponent - Custom component to render the action column
@Component({
  template: `
   <button mat-button [matMenuTriggerFor]="menu"><mat-icon>more_vert</mat-icon></button>
        <mat-menu #menu="matMenu">
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
}
