import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-audit-log',
  templateUrl: './audit-log.component.html',
  styleUrls: ['./audit-log.component.scss']
})
export class AuditLogComponent implements OnInit {
  lastActivityTimestamp: string;
  public rowData : any;
  columnDefs: any[];
  gridApi: any;

  constructor(
    private httpClient : HttpClient,

  ) {
    // Set the last activity timestamp
    this.lastActivityTimestamp = '2023-07-13 12:34 PM';

    // Define the column definitions for the ag-Grid
    this.columnDefs = [
      { headerName: 'S No', field: 'sno', valueGetter: "node.rowIndex + 1" },
      { headerName: 'Event Name', field: 'changeType' },
      { headerName: 'Timestamp', field: 'recordUpdateTimestamp' },
      { headerName: 'User ID', field: 'userIdentifier' }
    ];

    // Fetch and set the row data for the ag-Grid
  }

  fetchLogs(){
    this.httpClient.get('http://localhost:8080/audit-logs/getAll').subscribe((res:any)=>{
      this.rowData = res;
    })
  }
  ngOnInit(){
    this.fetchLogs()
  }

  onGridReady(param: any) {
    this.gridApi = param.api;
    this.gridApi.sizeColumnsToFit();
    
  }

}


