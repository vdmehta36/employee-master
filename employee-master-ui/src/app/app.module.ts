import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { EmployeeLoginComponent } from './employee-login/employee-login.component';
import { EmployeeRegistrationComponent } from './employee-registration/employee-registration.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';

import 'rxjs/operators/map';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { ValidationPatternsService } from './util/validation-patterns.service';
import { UserAuthGuard } from './guards/user-auth.guard';
import { EmployeeProfileComponent } from './employee-profile/employee-profile.component';

@NgModule({
  declarations: [
    AppComponent,
    EmployeeLoginComponent,
    EmployeeRegistrationComponent,
    EmployeeListComponent,
    EmployeeProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    ValidationPatternsService,
    UserAuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
