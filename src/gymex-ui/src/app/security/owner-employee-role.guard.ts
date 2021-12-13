import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {JwtService} from "./jwt.service";
import {SecurityUserService} from "./security-user.service";

@Injectable({
  providedIn: 'root'
})
export class OwnerEmployeeRoleGuard implements CanActivate {

  public constructor(
    private readonly jwtService: JwtService,
    private readonly securityUserService: SecurityUserService,
    private readonly router: Router
  ) {
  }

  public canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    if (this.securityUserService.isOwnerOrEmployee()) {
      return true;
    }

    this.jwtService.clearToken()
    this.securityUserService.clearUser()
    this.router.navigate(['/login']);
    return false;
  }
}
