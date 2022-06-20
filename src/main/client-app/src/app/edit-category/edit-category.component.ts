import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import ICategory from '../interfaces/category';
import { CategoryService } from '../shared/services/category.service';

@Component({
  selector: 'app-edit-category',
  templateUrl: './edit-category.component.html',
  styleUrls: ['./edit-category.component.css']
})
export class EditCategoryComponent implements OnInit {

  focus: any;
  focus1: any;

  public form: FormGroup;
  public categoryData: ICategory;

  constructor(private _categoryService: CategoryService, private router: Router, private fb: FormBuilder, private _route: ActivatedRoute) { }

  ngOnInit(): void {
    this._route.params.subscribe(params => {
      this._categoryService.GetCategory(+params['id']).subscribe(category => { // (+) convert*s string 'id' to a number
        this.categoryData = category;

        this.form = this.fb.group({
          name: [this.categoryData.name, Validators.required],
          description: [this.categoryData.description, Validators.required],
        });
      });
    });
  }

  onSubmit() {
    if (this.form.valid) {

      this.categoryData.name = this.form.get('name').value;
      this.categoryData.description = this.form.get('description').value;

      this._categoryService.UpdateCategory(this.categoryData).subscribe(response => {
        this.router.navigate(['/kategorie'])
      })
    }
  }
}
