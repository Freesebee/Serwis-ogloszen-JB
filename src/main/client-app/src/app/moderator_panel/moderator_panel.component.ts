import { Component, OnInit } from '@angular/core';
import { take, throwError, timeout } from "rxjs";
import IAd from "../interfaces/ad";
import { AdService } from "../shared/services/ad.service";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
    selector: 'app-moderator_panel',
    templateUrl: './moderator_panel.component.html',
    styleUrls: ['./moderator_panel.component.scss']
})

export class ModeratorPanelComponent implements OnInit {
    focus: any;
    focus1: any;

    public pendingAds: IAd[];

    constructor(private _adServide: AdService, private _router: Router) { }

    ngOnInit() {
        this._adServide
            .GetPendingAds()
            .pipe(
                timeout({
                    each: 3000,
                    with: () => throwError(() => new Error('AdService/GetAds request timed out')),
                })
            )
            .subscribe({
                next: (ads) => {
                    this.pendingAds = ads;
                },
                error: (er) => {
                    console.error(er);
                },
            });
    }

    public onApprove(adId: number) {
        this._adServide.ReviewAd(adId, true).subscribe((response) => {
            this.pendingAds = undefined;

            this._adServide.GetPendingAds().subscribe(ads => {
                this.pendingAds = ads;
            })
        });
    }

    public onDisapprove(adId: number) {
        this._adServide.ReviewAd(adId, false).subscribe((response) => {
            this.pendingAds = undefined;

            this._adServide.GetPendingAds().subscribe(ads => {
                this.pendingAds = ads;
            })
        });
    }
}
