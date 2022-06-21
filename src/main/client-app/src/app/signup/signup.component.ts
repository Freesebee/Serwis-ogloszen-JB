import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import IPersonalData from "../interfaces/personal-data";
import IAccount from "../interfaces/account";
import {AccountService} from "../shared/services/account.service";
import {PersonalDataService} from "../shared/services/personal-data.service";

@Component({
    selector: 'app-signup',
    templateUrl: './signup.component.html',
    styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
    test : Date = new Date();
    focus;
    focus1;
    focus2;
    focus3;
    focus4;
    focus5;
    focus6;
    public form: FormGroup;

    public : IAccount[];
    constructor(private _accountService: AccountService, private _personalDataService: PersonalDataService, private fb: FormBuilder, private router: Router) { }

    ngOnInit() {

        this.form = this.fb.group({
            login: ['', Validators.required],
            password: ['', Validators.required],
            email: ['', Validators.required],
            nickname: ['', Validators.required],
            name: ['', Validators.required],
            surname: ['', Validators.required],
            city: ['', Validators.required],
            street: ['', Validators.required],
        });
    }

    onSubmit() {
        if (this.form.valid) {
            const dataAcc: IAccount = {
                login: this.form.get('login').value,
                password: this.form.get('password').value,
                email: this.form.get('email').value,
                nickname: this.form.get('nickname').value,
                role: 'user',
                id: 0,
                createdBy: undefined,
                createdDate: undefined,
                modifiedBy: undefined,
                modifiedDate: undefined
            }

            const dataPer: IPersonalData = {
                name: this.form.get('name').value,
                surname: this.form.get('surname').value,
                city: this.form.get('city').value,
                street: this.form.get('street').value,
                id: 0,
                createdBy: undefined,
                createdDate: undefined,
                modifiedBy: undefined,
                modifiedDate: undefined
            }
            this._personalDataService.CreatePersonalData(dataPer).subscribe()
            this._accountService.CreateAccount(dataAcc).subscribe(response => {
                this.router.navigate(['/login/', response])
            })
        }
    }
}
