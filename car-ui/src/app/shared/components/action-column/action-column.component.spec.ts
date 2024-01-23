import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActionColumnComponent } from './action-column.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { MaterialModule } from '../../module/material.module';
import { DatePipe } from '@angular/common';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { CookieModule, CookieService } from 'ngx-cookie';
import {ICellRendererParams} from "ag-grid-community";
describe('ActionColumnComponent', () => {
  let component: ActionColumnComponent;
  let fixture: ComponentFixture<ActionColumnComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ActionColumnComponent],   
      imports:[HttpClientTestingModule,
        CookieModule.forRoot(),MaterialModule],
     providers: [CookieService, DatePipe],
     schemas: [NO_ERRORS_SCHEMA]    
    });
    fixture = TestBed.createComponent(ActionColumnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


  it('should initialize bulkEventCellValue on agInit', () => {
    const paramsMock = {data: {Status: 'InitialStatus'},};
    component.agInit(<ICellRendererParams>paramsMock);
    expect(component.bulkEventCellValue).toEqual(paramsMock);
  });
});
