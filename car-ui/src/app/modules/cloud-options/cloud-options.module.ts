import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { CloudOptionsRoutingModule } from "./cloud-options-routing.module";
import { TabBarComponent } from "./tab-bar/tab-bar.component";
import { SharedModule } from "src/app/shared/shared.module";
import { AgGridModule } from "ag-grid-angular";
import { MaterialModule } from "../../shared/module/material.module";
import { CloudOptionsComponent } from "./cloud-options.component";
import { CarsDataComponent } from "./cars-data/cars-data.component";

@NgModule({
  declarations: [TabBarComponent, CloudOptionsComponent, CarsDataComponent],
  imports: [
    CommonModule,
    CloudOptionsRoutingModule,
    MaterialModule,
    SharedModule,
    AgGridModule,
  ],
})
export class CloudOptionsModule {}
