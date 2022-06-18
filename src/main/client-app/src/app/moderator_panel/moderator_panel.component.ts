import { Component, OnInit } from '@angular/core';
import { take } from "rxjs";
import IModeratorPanel from "../interfaces/moderator_panel";
import { ModeratorPanelService } from "../shared/services/moderator_panel.service";

@Component({
    selector: 'app-moderator_panel',
    templateUrl: './moderator_panel.component.html',
    styleUrls: ['./moderator_panel.component.scss']
})

export class ModeratorPanelComponent implements OnInit {
  focus: any;
  focus1: any;

  public recentAds: IModeratorPanel[];

  constructor(private _moderator_panelService: ModeratorPanelService) {}

  ngOnInit() {
        this._moderator_panelService
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
