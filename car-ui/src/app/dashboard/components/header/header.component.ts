import { Component } from "@angular/core";
import { CartService } from "../../../shared/services/cart.service";
import { Router } from "@angular/router";

/**
 * Header for the dashboard.
 */
@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.scss"],
})
export class HeaderComponent {
  /**
   * Creates an instance of HeaderComponent.
   * @param {CartService} cartService - The cart service for managing the shopping cart.
   */
  constructor(
    public cartService: CartService,
    private router: Router,
  ) {}
  /** The title to be displayed in the header. */
  title: string = "Java UI Demo";
  /** The number of items in the shopping cart. */
  cartItemCount: number = 0;

  isCartUIVisible: boolean = false;

  ngOnInit() {
    if (this.router.url.includes("cart")) this.isCartUIVisible = true;
  }
  getCartItems() {
    this.cartService.getCartItems();
  }
}
