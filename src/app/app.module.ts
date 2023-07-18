import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatCardModule } from '@angular/material/card';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { ActionCellRendererComponent, HomeComponent } from './home/home.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { AddStaffComponent } from './add-staff/add-staff.component';
import { AuditLogComponent } from './audit-log/audit-log.component';
import { TasksComponent } from './tasks/tasks.component';
import { FormsModule } from '@angular/forms';
import { MatMenuModule } from '@angular/material/menu';
import { AgGridModule } from 'ag-grid-angular';
import { MatInputModule } from '@angular/material/input';
import { LogoutComponent } from './logout/logout.component';
import { HttpClientModule } from '@angular/common/http';
import { AddStudentComponent } from './add-student/add-student.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { ActionCellRendererComponent2, TeacherHomeComponent } from './teacher-home/teacher-home.component';
import { SuccessdialogComponent } from './successdialog/successdialog.component';
import { ViewStaffComponent } from './view-staff/view-staff.component';
import { IgxAvatarModule } from 'igniteui-angular';
import { EditStaffComponent } from './edit-staff/edit-staff.component';
import { AddClassroomComponent } from './add-classroom/add-classroom.component';
import { DeleteComponent } from './home/delete/delete.component';
import { AuthService } from './services/auth.service';
import { EnvService } from './env.service';
import {MatCheckboxModule} from '@angular/material/checkbox'
import { EditStudentComponent } from './edit-student/edit-student.component';
import { ActionCellRendererComponent1, ClassViewComponent } from './class-view/class-view.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { FeedbackViewComponent } from './feedback-view/feedback-view.component';
import { AiToolsComponent } from './ai-tools/ai-tools.component';
import { EditClassComponent } from './edit-class/edit-class.component';
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    EditStaffComponent,
    FooterComponent,
    LoginComponent,
    HomeComponent,
    AddStaffComponent,
    AuditLogComponent,
    SuccessdialogComponent,
    TasksComponent,
    LogoutComponent,
    AddStudentComponent,
    TeacherHomeComponent,
    SuccessdialogComponent,
    ViewStaffComponent,
    DeleteComponent,
    AddStudentComponent,
    ActionCellRendererComponent,
    ActionCellRendererComponent2,
    EditStudentComponent,
    AddClassroomComponent,
    ClassViewComponent,
    ActionCellRendererComponent1,
    FeedbackComponent,
    FeedbackViewComponent,
    AiToolsComponent,
    EditClassComponent
  ],
  imports: [
    HttpClientModule,
    MatDialogModule,
    BrowserModule,
    IgxAvatarModule,
    MatInputModule,
    MatMenuModule,
    AgGridModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatCheckboxModule,
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
  providers: [AuthService,EnvService],
  bootstrap: [AppComponent,
  ]
})
export class AppModule {
}
