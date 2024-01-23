import { Component } from '@angular/core';
import {ColDef, ColGroupDef} from "ag-grid-community";
import {Subscription} from "rxjs";
import {CommonService} from "../../../shared/services/common.service";
import {CarDetailsService} from "../../../shared/services/car-details.service";
import {MatDialog} from "@angular/material/dialog";
import {CartService} from "../../../shared/services/cart.service";
import {DatePipe} from "@angular/common";


@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.scss']
})
export class MyOrdersComponent {
  ordersColumnDef: (ColDef | ColGroupDef)[] = [];
  allTableHeaders: any[] = [];
  updatedOrderHeaders: any[] = [];
  isLoading = false;
  orderDataSubscription$!: Subscription;
  orderData: any[] = [];
  isCarsDataVisible: boolean = true;

  constructor(
      public commonService: CommonService,
      private carsDataService: CarDetailsService,
      private cartService : CartService,
      public dialog: MatDialog,
      private datePipe : DatePipe,
  ) {
  }

  ngOnInit(): void {
    this.getAllOrders();
  }

  setHeadersForCarsData(event: any) {
    this.updatedOrderHeaders = [];
    this.updatedOrderHeaders = this.commonService.setTableHeaders(
        event,
        this.allTableHeaders,
    );
    this.ordersColumnDef = this.updatedOrderHeaders;
  }

  getAllOrders() {
    this.isLoading = true;
    this.orderDataSubscription$ = this.cartService
        .getAllOrders()
        .subscribe((res) => {
          if (res) {
            this.ordersColumnDef = [
              {
                field: "productId",
                headerName: "Car Id",
                colId: "productId",
                minWidth: 100,
                filter: "agTextColumnFilter",
                suppressMenu: true,
                unSortIcon: true,
              },
              {
                field: "userId",
                headerName: "User Id",
                colId: "userId",
                minWidth: 170,
                filter: "agTextColumnFilter",
                suppressMenu: true,
                unSortIcon: true,
              },
              {
                field: "timestamp",
                headerName: "Time Stamp",
                colId: "timestamp",
                minWidth: 170,
                valueFormatter: (params) => this.formatTimestamp(params.value),
                filter: "agTextColumnFilter",
                suppressMenu: true,
                unSortIcon: true,
              },
              {
                field: "orderStatus",
                headerName: "Order Status",
                colId: "orderStatus",
                minWidth: 170,
                filter: "agTextColumnFilter",
                suppressMenu: true,
                unSortIcon: true,
              },

            ];
            this.orderData = res;
            this.updatedOrderHeaders = this.ordersColumnDef;
            this.allTableHeaders = this.ordersColumnDef;
          }
          this.isLoading = false;
        });
  }

  formatTimestamp(timestamp: any): string {
    if (!timestamp) return '';
    const formattedTimestamp = this.datePipe.transform(timestamp, 'medium');
    return formattedTimestamp ?? '';
  }

}
