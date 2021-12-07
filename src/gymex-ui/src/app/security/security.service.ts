import {Injectable} from "@angular/core";
import {JwtService} from "./jwt.service";
import {Observable} from "rxjs";
import {ApiService} from "../default/api.service";
import {Authentication, Register, SignIn, User} from "../default/models/security.model";
import {SecurityUserService} from "./security-user.service";

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  constructor(
    private readonly api: ApiService,
    private readonly jwtService: JwtService,
    private readonly userService: SecurityUserService
  ) {
  }

  login(data: SignIn): Observable<Authentication> {
    return this.api.post(`security/authenticate`, data)
  }

  logout(): void {
    this.jwtService.clearToken()
    this.userService.clearUser()
  }

  keepToken(token: string): void {
    this.jwtService.setToken(token)
  }

  register(data: Register): Observable<User> {
    return this.api.post(`security/register`, data)
  }

  getUser(): Observable<User> {
    return this.api.get(`user`)
  }

  keepUser(user: User): void {
    this.userService.setUser(user)
  }

}
