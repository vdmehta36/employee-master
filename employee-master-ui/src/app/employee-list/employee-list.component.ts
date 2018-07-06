import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.scss']
})
export class EmployeeListComponent implements OnInit {
  employees = [];
  key = 'employeeName';
  order = 'asc';
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.employees = [
      {
        'sno': 1,
        'employeeName': 'Rishikesh Kumar',
        'employeeNumber': 'A765565', 'password': 'Staffing', 'designation': 'Consultant', 'serviceLine': 'SI', 'role': 'zyz'
      },
      {
        'sno': 2,
        'employeeName': 'Vaibhav ',
        'employeeNumber': 'A765566', 'password': 'Staffing', 'designation': 'BTA', 'serviceLine': 'SI', 'role': 'zyz'
      },
      {
        'sno': 3,
        'employeeName': 'Vaibhav Kumar',
        'employeeNumber': 'A765586', 'password': '$2a$10$jNMP..jApYa3lksGYrdaGOil6/gUe1sKhevYMq9.wAgYUT.a6DqB6',
        'designation': 'BTA', 'serviceLine': 'SI', 'role': 'zyz'
      },
      {
        'sno': 4,
        'employeeName': 'Deepak Penaganti',
        'employeeNumber': '463570',
        'password': '$2a$10$3KLfdU/huFrTikAx1IgoYucSs/ycONIEnIvM.ERCJfpc/EienrM1y',
        'designation': 'BTA', 'serviceLine': 'SI', 'role': 'Developer'
      }];
    this.employees.sort(this.compare.bind(this));
    this.http.get(environment.apiUrl + `/employeedetail`)
      .subscribe((response: any) => {
        this.employees = response;
      });
  }

  sortBy(key: string) {
    const curr = !!key && key;
    if (curr === this.key) {
      this.order = this.order === 'asc' ? 'dec' : 'asc';
    } else {
      this.key = key;
      this.order = 'asc';
    }
    this.employees.sort(this.compare.bind(this));
  }


  compare(a?: any, b?: any) {
    if (a[this.key] < b[this.key]) {
      return this.order === 'asc' ? -1 : 1;
    }
    if (a[this.key] > b[this.key]) {
      return this.order === 'asc' ? 1 : -1;
    }
    return 0;
  }

}
