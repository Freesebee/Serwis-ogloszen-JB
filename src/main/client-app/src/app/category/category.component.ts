import { Component, OnInit } from '@angular/core';
import ICategory from '../interfaces/category';
import { CategoryService } from '../shared/services/category.service';
import {Router} from "@angular/router";
import {throwError, timeout} from "rxjs";

@Component({
    selector: 'app-category',
    templateUrl: './category.component.html',
    styleUrls: ['./category.component.scss']
})

export class CategoryComponent implements OnInit {
  focus: any;
  focus1: any;

    public recentCategories: ICategory[];

    constructor(private _categoryService: CategoryService, private _router: Router) {}

    ngOnInit() {
        this._categoryService
            .GetCategories()
            .pipe(
                timeout({
                    each: 3000,
                    with: () => throwError(() => new Error('CategoryService/GetCategories request timed out')),
                })
            )
            .subscribe({
                next: (categories) => {
                    this.recentCategories = categories;
                },
                error: (er) => {
                    console.error(er);
                },
            });
    }

}
