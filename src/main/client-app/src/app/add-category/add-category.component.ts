import { Component, OnInit } from '@angular/core';
import ICategory from "../interfaces/category";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CategoryService} from "../shared/services/category.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {
  focus: any;
  focus1: any;

  public form: FormGroup;
  public recentCategories: ICategory[];

  constructor(private _categoryService: CategoryService, private router: Router, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.form.valid) {
      const data: ICategory = {
        name: this.form.get('name').value,
        description: this.form.get('description').value,
        id: 0,
        createdBy: undefined,
        createdDate: undefined,
        modifiedBy: undefined,
        modifiedDate: undefined
      }
      this._categoryService.CreateCategory(data).subscribe(response => {
        this.router.navigate(['/kategorie/', response])
      })
    }
  }
}
