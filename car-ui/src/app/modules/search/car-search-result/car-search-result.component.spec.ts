import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarSearchResultComponent } from './car-search-result.component';

describe('CarSearchResultComponent', () => {
  let component: CarSearchResultComponent;
  let fixture: ComponentFixture<CarSearchResultComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CarSearchResultComponent]
    });
    fixture = TestBed.createComponent(CarSearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
