import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AddStaffComponent } from './add-staff/add-staff.component';
import { AuditLogComponent } from './audit-log/audit-log.component';
import { TasksComponent } from './tasks/tasks.component';
import { LogoutComponent } from './logout/logout.component';
import { AddStudentComponent } from './add-student/add-student.component';
import { ViewStaffComponent } from './view-staff/view-staff.component';
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'add-staff', component: AddStaffComponent},
  { path: 'audit-log', component: AuditLogComponent},
  { path: 'tasks', component: TasksComponent},
  // { path: 'view-staff', component: ViewStaffComponent}
  // { path: 'add-student', component: AddStudentComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
