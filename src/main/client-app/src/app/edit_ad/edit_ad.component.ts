import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import IAd from 'src/app/interfaces/ad';
import { AdService } from 'src/app/shared/services/ad.service';
import ICategory from '../interfaces/category';
import { CategoryService } from '../shared/services/category.service';

@Component({
  selector: 'app-edit_ad',
  templateUrl: './edit_ad.component.html',
  styleUrls: ['./edit_ad.component.scss']
})

export class EditAdComponent implements OnInit {
  focus: any;
  focus1: any;

  public adData: IAd;

  public form: FormGroup;

  public categories: ICategory[];

  constructor(private _categoryService: CategoryService, private fb: FormBuilder, private adService: AdService, private _route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this._route.params.subscribe(params => {
      this.adService.GetAd(+params['id']).subscribe(ad => { // (+) convert*s string 'id' to a number

        this._categoryService.GetCategories().subscribe(categories => {
          this.categories = categories;
          this.adData = ad;

          this.form = this.fb.group({
            title: [ad.title, Validators.required],
            content: [ad.content, Validators.required],
            categoryId: [ad.categoryByIdCategory.id, Validators.required],
          });
        });
      });
    });
  }

  onSubmit() {
    console.log(this.form.value) //TODO: Remove

    if (this.form.valid) {

      this.adData.title = this.form.get('title').value;
      this.adData.categoryByIdCategory = this.categories.find(c => c.id == this.form.get('categoryId').value);
      this.adData.content = this.form.get('content').value;
      this.adData.approval = undefined;

      this.adService.UpdateAd(this.adData).subscribe(response => {
        this.router.navigate(['/ogloszenia/', response.id])
      })
    }
  }
}
