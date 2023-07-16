import { Component } from '@angular/core';

@Component({
  selector: 'app-audit-log',
  templateUrl: './audit-log.component.html',
  styleUrls: ['./audit-log.component.scss']
})
export class AuditLogComponent {
  lastActivityTimestamp: string;
  rowData: any[];
  columnDefs: any[];

  constructor() {
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
    this.rowData = [
      { sno: 1, eventName: 'Event 1', timestamp: '2023-07-13 12:34 PM', comments: 'Comment 1' },
      { sno: 2, eventName: 'Event 2', timestamp: '2023-07-13 1:23 PM', comments: 'Comment 2' },
      { sno: 3, eventName: 'Event 3', timestamp: '2023-07-13 2:45 PM', comments: 'Comment 3' }
    ];
  }
}
