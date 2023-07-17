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
  styleUrls: ['./class-view.component.scss']
})
export class ClassViewComponent {
  username: string = '';
  schoolBackgroundImage = 'path/to/school-background-image.jpg';
  searchQuery!: string;
  public rowData : any;
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
      headerName:'Class Id',
      maxWidth: 150,
      field: 'classIdentifier',
      resizable: true,
    },
    {
      headerName: 'Grade',
      maxWidth: 150,
      field: 'gradeText',
      resizable: true,
    },
    {
      headerName: 'Division',
      maxWidth: 150,
      field: 'divisionText',
      resizable: true,
    },
    {
      headerName: 'Staff Id',
      maxWidth: 150,
      field: 'staffIdentifier',
      resizable: true,
    },
  ];
  public defaultColDef: ColDef = {
    flex: 1,
    filter: 'agTextColumnFilter',
    menuTabs: ['filterMenuTab'],
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

  fetchClasses(){
    this.httpclient.get('http://localhost:8080/classes/getAll')
    .subscribe((res) => {
      this.rowData = res;
      console.log(res);
      this.rowData.forEach((element:any) => {
        element.staffIdentifier = this.teachers.find((teacher:any)=>(teacher.staffId==element.staffIdentifier)).staffName || '';
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
    this.httpclient.get('http://localhost:8080/teacherDetails').subscribe((res : any, i = 0) => {
        res.forEach((e: any) => {
          this.teachers = res;
          console.log(res);
          
        })
      })
    this.fetchClasses();
    

    
  }

  selectedRows: any;


}
