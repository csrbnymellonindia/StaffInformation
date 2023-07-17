import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-feedback-view',
  templateUrl: './feedback-view.component.html',
  styleUrls: ['./feedback-view.component.scss'],
})
export class FeedbackViewComponent {
  public rowData: any;
  columnDefs: any[];
  gridApi: any;
  teachers: any;

  constructor(private httpClient: HttpClient) {
    // Set the last activity timestamp

    // Define the column definitions for the ag-Grid
    this.columnDefs = [
      {
        headerName: 'S No',
        field: 'sno',
        valueGetter: 'node.rowIndex + 1',
        width: 300,
        suppressSizeToFit: false,
      },
      {
        headerName: 'Feedback Description',
        field: 'feedbackDescription',
        flex:1,
        suppressSizeToFit: false,
      },
      {
        headerName: 'Staff Name',
        field: 'userIdentifier',
        suppressSizeToFit: false,
      },
      
    ];
  }

  fetchFeedbacks() {
    this.httpClient
      .get('http://localhost:8080/feedbacks/getAll')
      .subscribe((res: any) => {
        this.rowData = res;
        this.rowData.forEach((element: any) => {
          console.log(element.userIdentifier);
          
          element.userIdentifier =
            this.teachers.find(
              (teacher: any) => teacher.staffId == element.userIdentifier
            ).staffName || '';
        });
      });
      
  }
  ngOnInit() {
    this.httpClient
      .get('http://localhost:8080/teacherDetails')
      .subscribe((res: any, i = 0) => {
        res.forEach((e: any) => {
          console.log('response of teacherDetails', res);
          this.teachers = res;
          console.log(res);
        });
      });
    this.fetchFeedbacks();
  }

  onGridReady(param: any) {
    this.gridApi = param.api;
    this.gridApi.sizeColumnsToFit();
  }
}
