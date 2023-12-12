import {Injectable} from "@angular/core";
import {
  HTTP_INTERCEPTORS,
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from "@angular/common/http";
import {Observable, tap} from "rxjs";
import {TokenStorageService} from "../service/token-storage.service";
import {Router} from "@angular/router";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()

export class AuthInterceptor implements HttpInterceptor {
  constructor(private token: TokenStorageService, private router: Router) {

  }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("inside interceptor");
    let authReq = req;
    const loginPath = '/user/signin';
    const token = this.token.getToken();
    console.log(token);
    if (token != null) {
       authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });

    }
    return next.handle(authReq).pipe(tap(()=>{},
      (err :any)=>{
        if (err instanceof HttpErrorResponse) {
          if (err.status !== 401 || window.location.pathname === loginPath) {
         // if (err.status !== 401 ) {
            return;
          }
          this.token.signOut();
          window.location.href=loginPath;

        }


      }));
  }

}
export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];
