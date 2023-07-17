import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { StatusPanelDef } from 'ag-grid-community';
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
  userdata: any;

  constructor(
    private httpClient : HttpClient,

  ) {
    // Set the last activity timestamp
    this.lastActivityTimestamp = moment().toString();

    // Define the column definitions for the ag-Grid
    this.columnDefs = [
      { headerName: 'S No', field: 'sno', valueGetter: "node.rowIndex + 1" ,width:300,suppressSizeToFit: false},
      { headerName: 'Event Name', field: 'changeType',width:300 ,suppressSizeToFit: false,enableRowGroup:true},
      { headerName: 'Timestamp', field: 'recordUpdateTimestamp',width:400 ,suppressSizeToFit: false},
      { headerName: 'User ID', field: 'userIdentifier',width:300,suppressSizeToFit: false,enableRowGroup:true }
    ];
  }
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
  fetchLogs(){
    this.httpClient.get('http://localhost:8080/audit-logs/getAll').subscribe((res:any)=>{
      this.rowData = res;
      console.log(res);
      console.log(this.userdata);
      res.forEach((element: any) => {
        element.userIdentifier = this.userdata.find((e:any)=>e.id==element.userIdentifier+1).username || ''
      });
    })
  }
  ngOnInit(){
    this.httpClient.get('http://localhost:8080/getUsers').subscribe((res:any)=>{
      this.userdata = res;
    })
    this.fetchLogs()
  }

  onGridReady(param: any) {
    this.gridApi = param.api;
    this.gridApi.sizeColumnsToFit();
  }

}


