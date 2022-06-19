import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {AuthService} from "../auth.service";
import {Observable} from "rxjs";
import {TokenService} from "../token.service";

@Injectable()
export class JwtTokenInterceptor implements HttpInterceptor {

    constructor(public token: TokenService) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let interceptedRequest = request.clone({
            setHeaders: {
                Authorization: `Bearer ${this.token.getToken()}`
            }
        });

        return next.handle(interceptedRequest);
    }
}