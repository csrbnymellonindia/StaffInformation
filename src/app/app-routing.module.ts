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
import { ClassViewComponent } from './class-view/class-view.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { FeedbackViewComponent } from './feedback-view/feedback-view.component';
import { AiToolsComponent } from './ai-tools/ai-tools.component';
import { EditClassComponent } from './edit-class/edit-class.component';
const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate:[AuthGuard]  },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'add-staff', component: AddStaffComponent, canActivate:[AuthGuard]},
  { path: 'view-staff', component: ViewStaffComponent, canActivate:[AuthGuard]},
  { path: 'edit-staff', component: EditStaffComponent, canActivate:[AuthGuard]},
  { path: 'edit-student', component: EditStudentComponent, canActivate:[AuthGuard]},
  { path: 'edit-class', component: EditClassComponent, canActivate:[AuthGuard]},
  { path: 'audit-log', component: AuditLogComponent, canActivate:[AuthGuard]},
  { path: 'tasks', component: TasksComponent, canActivate:[AuthGuard]},
  { path: 'student-view', component: TeacherHomeComponent},
   { path: 'add-student', component: AddStudentComponent, canActivate:[AuthGuard] },
   {path : 'class-view', component: ClassViewComponent, canActivate: [AuthGuard]},
   {path : 'add-classroom', component: AddClassroomComponent, canActivate : [AuthGuard]},
   {path : 'feedback', component: FeedbackComponent, canActivate : [AuthGuard]},
   {path : 'ai-tools', component: AiToolsComponent, canActivate : [AuthGuard]},
   {path : 'feedback-view', component: FeedbackViewComponent, canActivate : [AuthGuard]},
  {path:'',redirectTo:'/home',pathMatch:'full'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
