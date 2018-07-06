import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-registration',
  templateUrl: './employee-registration.component.html',
  styleUrls: ['./employee-registration.component.scss']
})
export class EmployeeRegistrationComponent implements OnInit {
  visible = false;
  modal = false;
  employeeName: string;
  employeeNumber: string;
  password: string;
  designation: string;
  serviceLine: string;
  role: string;
  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() {
  }

  register(form) {
    // alert(JSON.stringify(form.value));
    if (form.valid) {
      this.http.post(environment.apiUrl + `/EmployeeRegistration`, form.value)
        .subscribe((response: any) => {
          if (!!response && response.status === 'SUCCESS') {
            sessionStorage.setItem('user', JSON.stringify(response));
            this.router.navigate(['/dashboard']);
          }
          // alert(JSON.stringify(response));
        });
    }
  }

}
