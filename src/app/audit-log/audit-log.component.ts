import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-audit-log',
  templateUrl: './audit-log.component.html',
  styleUrls: ['./audit-log.component.scss']
})
export class AuditLogComponent {
  lastActivityTimestamp: string;
  public rowData : any;
  columnDefs: any[];

  constructor(
    private httpClient : HttpClient,

  ) {
    // Set the last activity timestamp
    this.lastActivityTimestamp = '2023-07-13 12:34 PM';

    // Define the column definitions for the ag-Grid
    this.columnDefs = [
      { headerName: 'S No', field: 'sno' },
      { headerName: 'Event Name', field: 'eventName' },
      { headerName: 'Timestamp', field: 'timestamp' },
      { headerName: 'Comments', field: 'comments' }
    ];

    // Fetch and set the row data for the ag-Grid
  }

  ngOnInit(){
  }
}


