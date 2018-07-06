import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'app';

  constructor(private router: Router) { }

  getUser() {
    const user: any = !!sessionStorage.getItem('user') ? JSON.parse(sessionStorage.getItem('user')) : '';
    if (!!user) {
      return user.employeeName;
    } else {
      return '';
    }
  }

  logout() {
    sessionStorage.removeItem('user');
    this.router.navigate(['/login']);
  }
}
