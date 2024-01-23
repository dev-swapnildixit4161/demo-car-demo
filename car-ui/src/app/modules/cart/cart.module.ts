import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { CartComponent } from "./cart.component";
import { CartItemsComponent } from "./cart-items/cart-items.component";
import { CartRoutingModule } from "./cart-routing.module";
import { MaterialModule } from "../../shared/module/material.module";
import { DashboardModule } from "../../dashboard/dashboard.module";
@NgModule({
  declarations: [CartComponent, CartItemsComponent],
  imports: [CommonModule, CartRoutingModule, MaterialModule, DashboardModule],
})
export class CartModule {}
