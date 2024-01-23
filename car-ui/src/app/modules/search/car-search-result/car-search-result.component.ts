import { Component } from "@angular/core";
import {CartService} from "../../../shared/services/cart.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ICellRendererParams} from "ag-grid-community";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: "app-car-search-result",
  templateUrl: "./car-search-result.component.html",
  styleUrls: ["./car-search-result.component.scss"],
})
export class CarSearchResultComponent {
  constructor(    private cartService: CartService,
                  private snackBar: MatSnackBar,
                  private route : ActivatedRoute

  ) {
  }
  carsData: CarDetails1[] = [];
  page: number = 1;
  itemsPerPage: number = 5;
  selectedCategory: string = 'All';
  searchTerm: string = '';
  isDataPresent = true;
  ngOnInit() {
    this.route.queryParams.subscribe((queryParams) => {
      this.selectedCategory = queryParams['category'] || 'All';
      this.searchTerm = queryParams['term'] || '';
      this.isDataPresent = true;
      this.getAllSearchedCars();
  });
  }

  public bulkEventCellValue!: any;
  public carId!: string;

  agInit(params: ICellRendererParams): void {
    this.bulkEventCellValue = params;
    this.carId = params.data.carId;
  }
  addToCart(carId : string): void {
    this.cartService.addToCart(carId).subscribe((response) => {
      if (response != null) {
        this.cartService.incrementCartItemCount();
        this.showSnackBar("Item has been added to the cart");
      } else {
        console.error("Failed to add to cart:");
      }
    });
  }
  private showSnackBar(message: string) {
    this.snackBar.open(message, "Close", {
      duration: 3000,
    });
  }

  getAllSearchedCars() {
    let searchObservable;
    if (this.selectedCategory === 'Id') {
      searchObservable = this.cartService.searchCarsById(this.searchTerm);
    } else if (this.selectedCategory === 'Brand') {
      searchObservable = this.cartService.searchCarsByBrand(this.searchTerm);
    } else if (this.selectedCategory === 'Price') {
      searchObservable = this.cartService.searchCarsByPrice(this.searchTerm);
    } else if (this.selectedCategory === 'Mileage') {
      searchObservable = this.cartService.searchCarsByMileage(this.searchTerm);
    } else {
      searchObservable = this.cartService.searchAllCars();
    }

    searchObservable
        .subscribe(
            (response) => {
              if (!Array.isArray(response) && response != null) {
                this.carsData = [response];
              } else {
                this.carsData = response;
              }
            },
            (error) => {
              this.handleErrorResponse(error);
              this.isDataPresent = false;
            }
        );
  }

  private handleErrorResponse(error: any): void {
    if (error.status === 500 || error.status === 404) {
      this.isDataPresent = false;
    }
  }
}


interface CarDetails1 {
  brand_id: number;
  brand_name: string;
  carId : string;
  model: string;
  year: number;
  color: string;
  mileage: number;
  price: string;
  location: string;
}
