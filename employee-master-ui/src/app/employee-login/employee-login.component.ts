import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { ValidationPatternsService } from '../util/validation-patterns.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-employee-login',
  templateUrl: './employee-login.component.html',
  styleUrls: ['./employee-login.component.scss']
})
export class EmployeeLoginComponent implements OnInit {
  modal = false;
  visible = false;
  serverError = '';
  employeeNumber: String;
  passwords: String;
  constructor(private http: HttpClient,
    private patternService: ValidationPatternsService,
    private router: Router) { }

  ngOnInit() {
    this.patternService.incrementalMatcher('DeepakPenaganti123$');
  }

  login(formData) {
    this.http.post(environment.apiUrl
      + `/EmployeeAuthentication`,
      formData).subscribe((response: any) => {
        if (!!response && response.employeeName) {
          sessionStorage.setItem('user', JSON.stringify(response));
          this.router.navigate(['/dashboard']);
        }
      }, error => {
        if (error.status === 404) {
          this.serverError = 'Invalid Employee number/Password';
        }
      });
  }

}
