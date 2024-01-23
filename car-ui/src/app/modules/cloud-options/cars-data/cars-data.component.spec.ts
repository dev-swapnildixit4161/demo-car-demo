import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarsDataComponent } from './cars-data.component';

describe('CarsDataComponent', () => {
  let component: CarsDataComponent;
  let fixture: ComponentFixture<CarsDataComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CarsDataComponent]
    });
    fixture = TestBed.createComponent(CarsDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
