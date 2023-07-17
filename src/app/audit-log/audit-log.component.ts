import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';

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
    this.lastActivityTimestamp = moment().toString();

    // Define the column definitions for the ag-Grid
    this.columnDefs = [
      { headerName: 'S No', field: 'sno', valueGetter: "node.rowIndex + 1" ,width:300,suppressSizeToFit: false},
      { headerName: 'Event Name', field: 'changeType',width:300 ,suppressSizeToFit: false},
      { headerName: 'Timestamp', field: 'recordUpdateTimestamp',width:400 ,suppressSizeToFit: false},
      { headerName: 'User ID', field: 'userIdentifier',width:300,suppressSizeToFit: false }
    ];
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


