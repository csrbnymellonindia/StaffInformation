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
import { AuthGuard } from './shared/authguard.guard';
import { ViewStaffComponent } from './view-staff/view-staff.component';
import { EditStaffComponent } from './edit-staff/edit-staff.component';
import { AddClassroomComponent } from './add-classroom/add-classroom.component';
import { TeacherHomeComponent } from './teacher-home/teacher-home.component';
import { EditStudentComponent } from './edit-student/edit-student.component';
const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate:[AuthGuard]  },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'add-staff', component: AddStaffComponent, canActivate:[AuthGuard]},
  { path: 'view-staff', component: ViewStaffComponent, canActivate:[AuthGuard]},
  { path: 'edit-staff', component: EditStaffComponent, canActivate:[AuthGuard]},
  { path: 'edit-student', component: EditStudentComponent, canActivate:[AuthGuard]},
  { path: 'audit-log', component: AuditLogComponent, canActivate:[AuthGuard]},
  { path: 'tasks', component: TasksComponent, canActivate:[AuthGuard]},
  { path: 'student-view', component: TeacherHomeComponent},
   { path: 'add-student', component: AddStudentComponent, canActivate:[AuthGuard] },
  {path:'',redirectTo:'/home',pathMatch:'full'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
