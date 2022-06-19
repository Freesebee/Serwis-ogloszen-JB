import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import IAd from 'src/app/interfaces/ad';
import { AdService } from 'src/app/shared/services/ad.service';
import {throwError, timeout} from "rxjs";

@Component({
    selector: 'app-edit_ad',
    templateUrl: './edit_ad.component.html',
    styleUrls: ['./edit_ad.component.scss']
})

export class EditAdComponent implements OnInit {
  focus: any;
  focus1: any;

  public adData: IAd;

  constructor(private _adService: AdService, private _route: ActivatedRoute) { }

  ngOnInit(): void {
      this._adService
          .UpdateAd(this.adData)
          .pipe(
              timeout({
                  each: 3000,
                  with: () => throwError(() => new Error('AdService/GetAds request timed out')),
              })
          )
          .subscribe({
              next: (ads) => {
                  this.adData = ads;
              },
              error: (er) => {
                  console.error(er);
              },
          });
  }

}
