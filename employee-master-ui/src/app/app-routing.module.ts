import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeLoginComponent } from './employee-login/employee-login.component';
import { EmployeeRegistrationComponent } from './employee-registration/employee-registration.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { UserAuthGuard } from './guards/user-auth.guard';

const routes: Routes = [
  {path: 'login', component: EmployeeLoginComponent},
  {path: 'register', component: EmployeeRegistrationComponent},
  {path: 'dashboard', component: EmployeeListComponent, canActivate: [UserAuthGuard]},
  {path: '', pathMatch: 'full', redirectTo: '/login'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
