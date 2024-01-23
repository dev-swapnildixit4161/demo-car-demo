import { Component } from "@angular/core";
import { CarsDataComponent } from "../../../modules/cloud-options/cars-data/cars-data.component";

@Component({
  selector: "app-data-not-found",
  templateUrl: "./data-not-found.component.html",
  styleUrls: ["./data-not-found.component.scss"],
})
export class DataNotFoundComponent extends CarsDataComponent {
  generateData() {
    this.addBulkData();
  }
}
