import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CarSearchResultComponent} from "./car-search-result/car-search-result.component";
const routes: Routes = [
  {
    path: "",
    component: CarSearchResultComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SearchRoutingModule { }
