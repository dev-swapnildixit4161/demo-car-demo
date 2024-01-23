import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { CloudOptionsComponent } from "./cloud-options.component";
import { CarsDataComponent } from "./cars-data/cars-data.component";

const routes: Routes = [
  {
    path: "",
    component: CloudOptionsComponent,
    children: [
      {
        path: "",
        component: CarsDataComponent,
      },
      {
        path: "gcp",
        component: CarsDataComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CloudOptionsRoutingModule {}
