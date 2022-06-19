import { Injectable } from "@angular/core";
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { catchError, Observable, throwError } from "rxjs";
import { TokenService } from "../services/token.service";

@Injectable()
export class JwtTokenInterceptor implements HttpInterceptor {

    constructor(public token: TokenService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        let interceptedRequest = request;

        if (this.token.getToken()) {
            interceptedRequest = request.clone(
                {
                    setHeaders: {
                        Authentication: `Bearer ${this.token.getToken()}`
                    }
                });
        }

        return next.handle(interceptedRequest).pipe(
            catchError((err) => {
                console.error(err);
                return throwError(() => new Error(err));
            })
        );
    }
}