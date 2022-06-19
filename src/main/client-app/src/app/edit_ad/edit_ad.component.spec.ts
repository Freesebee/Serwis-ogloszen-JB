import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAdComponent } from './edit_ad.component';

describe('EditAdComponent', () => {
  let component: EditAdComponent;
  let fixture: ComponentFixture<EditAdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditAdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
