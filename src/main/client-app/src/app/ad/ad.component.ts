import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { take, throwError, timeout } from "rxjs";
import IAd from "../interfaces/ad";
import { AdService } from "../shared/services/ad.service";

@Component({
  selector: "app-ogloszenia",
  templateUrl: "./ad.component.html",
  styleUrls: ["./ad.component.scss"],
})
export class AdComponent implements OnInit {
  focus: any;
  focus1: any;

  public recentAds: IAd[];

  constructor(private _adServide: AdService, private _router: Router) {}

  ngOnInit() {
    this._adServide
      .GetAds()
      .pipe(
        timeout({
          each: 3000,
          with: () => throwError(() => new Error('AdService/GetAds request timed out')),
        })
      )
      .subscribe({
        next: (ads) => {
          this.recentAds = ads;
        },
        error: (er) => {
          console.error(er);
        },
      });
  }
}
