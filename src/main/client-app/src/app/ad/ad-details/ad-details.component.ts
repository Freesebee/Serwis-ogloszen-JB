import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import IAccount from 'src/app/interfaces/account';
import IAd from 'src/app/interfaces/ad';
import { AccountService } from 'src/app/shared/services/account.service';
import { AdService } from 'src/app/shared/services/ad.service';

@Component({
  selector: 'app-ad-details',
  templateUrl: './ad-details.component.html',
  styleUrls: ['./ad-details.component.css']
})
export class AdDetailsComponent implements OnInit {

  public adData: IAd;
  public currentUser: IAccount;
  public canModifyAd = false;

  constructor(private _adService: AdService, private _route: ActivatedRoute, private router: Router, private accountService: AccountService) { }

  ngOnInit(): void {
    this._route.params.subscribe(params => {
      this._adService.GetAd(+params['id']).subscribe(ad => { // (+) convert*s string 'id' to a number
        this.adData = ad;
        this.accountService.GetCurrentAccount().subscribe(account => {
          this.currentUser = account;
          this.canModifyAd = this.adData.accountByIdAccount.id == account.id || account.role == 'admin'
        })
      }) 
   });
  }

  onDeleteAd() {
    this._adService.DeleteAd(this.adData.id).subscribe((response) => {
      this.router.navigate(['/ogloszenia']);
    })
  }

  onUpdateAd() {
    this.router.navigate(['/edit/', this.adData.id])
  }
}
