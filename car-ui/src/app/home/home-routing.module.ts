import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { CarBrandsComponent } from "./car-brands/car-brands.component";
import { CarsListComponent } from "./cars-list/cars-list.component";
import { HomeComponent } from "./home.component";
import { DataNotFoundComponent } from "./data-not-found/data-not-found.component";

const routes: Routes = [
  {
    path: "",
    component: HomeComponent,
    children: [
      { path: "azure", component: CarBrandsComponent },
      { path: "gcp", component: CarBrandsComponent },
      {
        path: "gcp/carList/:brandName",
        component: CarsListComponent,
      },
      {
        path: "azure/carList/:brandName",
        component: CarsListComponent,
      },
      {
        path: "noData",
        component: DataNotFoundComponent,
      },
      {
        path: "",
        redirectTo: "gcp",
        pathMatch: "full",
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomeRoutingModule {}
