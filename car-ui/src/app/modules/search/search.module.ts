import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { SearchRoutingModule } from "./search-routing.module";
import { CarSearchResultComponent } from "./car-search-result/car-search-result.component";
import { DashboardModule } from "../../dashboard/dashboard.module";
import { HomeModule } from "../../home/home.module";
import {NgxPaginationModule} from "ngx-pagination";

@NgModule({
  declarations: [CarSearchResultComponent],
    imports: [CommonModule, SearchRoutingModule, DashboardModule, HomeModule, NgxPaginationModule],
})
export class SearchModule {}
