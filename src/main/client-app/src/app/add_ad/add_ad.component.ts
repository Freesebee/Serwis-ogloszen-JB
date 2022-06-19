import { Component, OnInit } from '@angular/core';
import ICategory from '../interfaces/category';
import { CategoryService } from '../shared/services/category.service';
import IAccount from '../interfaces/account';

@Component({
    selector: 'app-add_ad',
    templateUrl: './add_ad.component.html',
    styleUrls: ['./add_ad.component.scss']
})

export class AddAdComponent implements OnInit {
  focus: any;
  focus1: any;

  public categories: ICategory[];
  constructor(private _categoryService: CategoryService) { }

  ngOnInit() {
    this._categoryService.GetCategories().subscribe(categories => {
      this.categories = categories;
    });
  }
}
