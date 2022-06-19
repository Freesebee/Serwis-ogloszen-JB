import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import IAd from 'src/app/interfaces/ad';
import { AdService } from 'src/app/shared/services/ad.service';

@Component({
  selector: 'app-ad-details',
  templateUrl: './ad-details.component.html',
  styleUrls: ['./ad-details.component.css']
})
export class AdDetailsComponent implements OnInit {

  public adData: IAd;

  constructor(private _adService: AdService, private _route: ActivatedRoute) { }

  ngOnInit(): void {
    this._route.params.subscribe(params => {
      this._adService.GetAd(+params['id']).subscribe(ad => { // (+) convert*s string 'id' to a number
        this.adData = ad;
      }) 
   });
  }

}
