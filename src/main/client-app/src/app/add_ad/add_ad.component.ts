import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import IAd from '../interfaces/ad';
import ICategory from '../interfaces/category';
import { AdService } from '../shared/services/ad.service';
import { CategoryService } from '../shared/services/category.service';

@Component({
  selector: 'app-add_ad',
  templateUrl: './add_ad.component.html',
  styleUrls: ['./add_ad.component.scss']
})

export class AddAdComponent implements OnInit {
  focus: any;
  focus1: any;

  public form: FormGroup;

  public categories: ICategory[];
  constructor(private _categoryService: CategoryService, private fb: FormBuilder, private adService: AdService, private router: Router) { }

  ngOnInit() {
    this._categoryService.GetCategories().subscribe(categories => {
      this.categories = categories;
    });

    this.form = this.fb.group({
      title: ['', Validators.required],
      content: ['', Validators.required],
      categoryId: [null, Validators.required],
    });
  }

  onSubmit() {
    console.log(this.form.value) //TODO: Remove
    if (this.form.valid) {
      const data: IAd = {
        title: this.form.get('title').value,
        content: this.form.get('content').value,
        categoryByIdCategory: this.categories.find(c => c.id == this.form.get('categoryId').value),
        city: 'Białystok',
        street: 'Wiejska',
        approval: undefined,
        accountByIdAccount: undefined,
        id: 0,
        createdBy: undefined,
        createdDate: undefined,
        modifiedBy: undefined,
        modifiedDate: undefined
      }
      this.adService.CreateAd(data).subscribe(response => {
        this.router.navigate(['/ogloszenia/', response.id])
      })
    }
  }
}
