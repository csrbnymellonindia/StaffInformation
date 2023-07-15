import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatCardModule } from '@angular/material/card';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule} from '@angular/material/form-field';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent, SuccessDialog } from './login/login.component';
import { ActionCellRendererComponent, HomeComponent } from './home/home.component'
import { ReactiveFormsModule } from '@angular/forms'
import {MatIconModule } from '@angular/material/icon'
import {MatDialogModule } from '@angular/material/dialog'
import { MatButtonModule} from '@angular/material/button';
import { AddStaffComponent } from './add-staff/add-staff.component';
import { AuditLogComponent } from './audit-log/audit-log.component';
import { TasksComponent } from './tasks/tasks.component'
import { FormsModule } from '@angular/forms'
import {MatMenuModule} from '@angular/material/menu';
import { AgGridModule } from 'ag-grid-angular';
import { MatInputModule } from '@angular/material/input';
import { LogoutComponent } from './logout/logout.component';
import { HttpClientModule } from '@angular/common/http';
import { AddStudentComponent } from './add-student/add-student.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MatDatepickerModule } from '@angular/material/datepicker'
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select'

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    HomeComponent,
    AddStaffComponent,
    AuditLogComponent,
    SuccessDialog,
    TasksComponent,
    ActionCellRendererComponent,
    LogoutComponent,
    AddStudentComponent
  ],
  imports: [
    HttpClientModule,
    MatDialogModule,
    BrowserModule,
    MatInputModule,
    MatMenuModule,
    AgGridModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatIconModule,
    FormsModule,
    MatButtonModule,
    MatInputModule,
    MatTabsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }