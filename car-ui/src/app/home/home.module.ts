import { NgModule } from "@angular/core";
import { CommonModule, NgOptimizedImage } from "@angular/common";
import { CarBrandsComponent } from "./car-brands/car-brands.component";
import { CarsListComponent } from "./cars-list/cars-list.component";
import { DataNotFoundComponent } from "./data-not-found/data-not-found.component";
import { HomeComponent } from "./home.component";
import { PageNotFoundComponent } from "./page-not-found/page-not-found.component";
import { MaterialModule } from "../shared/module/material.module";
import { HomeRoutingModule } from "./home-routing.module";
import { CarsdataCardComponent } from "./cars-list/carsdata-card/carsdata-card.component";
import { NgxPaginationModule } from "ngx-pagination";
@NgModule({
  declarations: [
    CarBrandsComponent,
    CarsListComponent,
    DataNotFoundComponent,
    PageNotFoundComponent,
    HomeComponent,
    CarsdataCardComponent,
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    MaterialModule,
    NgOptimizedImage,
    NgxPaginationModule,
  ],
  exports: [HomeComponent, CarsdataCardComponent],
})
export class HomeModule {}
