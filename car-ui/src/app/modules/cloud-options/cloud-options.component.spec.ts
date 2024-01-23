import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CloudOptionsComponent } from './cloud-options.component';

describe('CloudOptionsComponent', () => {
  let component: CloudOptionsComponent;
  let fixture: ComponentFixture<CloudOptionsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CloudOptionsComponent]
    });
    fixture = TestBed.createComponent(CloudOptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
