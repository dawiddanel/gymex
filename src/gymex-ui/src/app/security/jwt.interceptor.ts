import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JwtService} from "./jwt.service";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(
    private readonly jwtService: JwtService
  ) {
  }

  public intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = this.jwtService.getToken();

    if (!this.isNullOrEmpty(token)) {
      const jwtRequest = request.clone({
        headers: request.headers.set('Authorization', 'Bearer ' + token)
      });

      return next.handle(jwtRequest);
    } else {
      return next.handle(request);
    }
  }

  isNullOrEmpty(value: string): boolean {
    return value === null || value === undefined || value.length === 0;
  }
}
