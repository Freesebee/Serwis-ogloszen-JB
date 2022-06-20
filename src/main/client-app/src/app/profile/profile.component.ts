import { Component, OnInit } from '@angular/core';
import IAccount from '../interfaces/account';
import { AccountService } from '../shared/services/account.service';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss']
})

export class ProfileComponent implements OnInit {

    public account: IAccount;

    constructor(private accountService: AccountService) { }

    ngOnInit() {
        this.accountService.GetCurrentAccount().subscribe((response) => {
            this.account = response;
            console.log(response)
        })
    }

}
