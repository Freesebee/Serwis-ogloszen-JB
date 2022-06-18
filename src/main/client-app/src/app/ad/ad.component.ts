import { Component, OnInit } from "@angular/core";
import { take } from "rxjs";
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

  constructor(private _adServide: AdService) {}

  ngOnInit() {
    this._adServide
      .GetAds()
      .pipe(take(3))
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
