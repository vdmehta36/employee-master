import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';

@Injectable()
export class UserAuthGuard implements CanActivate {
  constructor(private router: Router) { }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    const user = !!sessionStorage.getItem('user') && JSON.parse(sessionStorage.getItem('user'));
    if (!user.employeeName) {
      this.router.navigate(['/login']);
    } else {
      return true;
    }
  }
}
