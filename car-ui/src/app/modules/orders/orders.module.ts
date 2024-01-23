import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MaterialModule} from "../../shared/module/material.module";
import { OrdersRoutingModule } from './orders-routing.module';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import {SharedModule} from "../../shared/shared.module";
import {AgGridModule} from "ag-grid-angular";


@NgModule({
  declarations: [
    MyOrdersComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    OrdersRoutingModule,
    CommonModule,
    SharedModule,
    AgGridModule
  ]
})
export class OrdersModule { }
