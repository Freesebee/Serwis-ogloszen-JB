import {Component, OnInit} from '@angular/core';
import {Credentials} from "./credentials";
import {TokenService} from "../shared/services/token.service";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    focus;
    focus1;

    credentials: Credentials = new Credentials('', '');

    constructor(private tokenService: TokenService) {
    }

    ngOnInit() {}

    public login(): void {
        let credentialsAsJson = {
            "userName": this.credentials.username,
            "password": this.credentials.password
        }
        let response = this.tokenService.generateToken(credentialsAsJson);
        response.subscribe(data=>{
          this.tokenService.saveToken(data)
          console.log(data);
        })
    }
}
