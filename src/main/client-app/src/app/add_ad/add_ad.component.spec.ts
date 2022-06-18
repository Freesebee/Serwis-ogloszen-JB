import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAdComponent } from './add_ad.component';

describe('AddAdComponent', () => {
  let component: AddAdComponent;
  let fixture: ComponentFixture<AddAdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddAdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
