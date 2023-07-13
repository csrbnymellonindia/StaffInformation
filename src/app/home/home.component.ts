import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  schoolBackgroundImage = 'path/to/school-background-image.jpg';
  searchQuery!: string;
  rowData: any[] = [];
  columnDefs: any[] = [
    { headerName: 'S.No', field: 'sNo' },
    { headerName: 'Staff Name', field: 'staffName' },
    { headerName: 'Primary Contact', field: 'primaryContact' },
    { headerName: 'Secondary Contact', field: 'secondaryContact' },
    { headerName: 'Address', field: 'address' },
    { headerName: 'Email', field: 'email' },
    { headerName: 'Whatsapp No', field: 'whatsappNo' },
    { headerName: 'Action', field: 'action', cellRenderer: 'actionCellRenderer' }
  ];
  defaultColDef = {
    sortable: true,
    filter: true
  };
  frameworkComponents: any = {
    actionCellRenderer: ActionCellRendererComponent
  };
  
  constructor() {
    // Initialize the rowData with your data
    this.rowData = [
      // ...
    ];
  }
}

// ActionCellRendererComponent - Custom component to render the action column
@Component({
  template: `
    <button  color="primary" (click)="edit()">Edit</button>
    <button  color="warn" (click)="delete()">Delete</button>
  `
})
export class ActionCellRendererComponent {
  // Implement the edit and delete functions
  edit() {
    // ...
  }

  delete() {
    // ...
  }
}
