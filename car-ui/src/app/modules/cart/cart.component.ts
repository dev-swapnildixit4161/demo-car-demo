import { Component } from "@angular/core";
import { CartItem } from "../../shared/module/cart-item.model";
import { CartService } from "../../shared/services/cart.service";

@Component({
  selector: "app-cart",
  templateUrl: "./cart.component.html",
  styleUrls: ["./cart.component.scss"],
})
export class CartComponent {
  cartItems: CartItem[] = [];

  constructor(private cartService: CartService) {}

  ngOnInit() {
    this.getCartItem();
  }
  getCartItem() {
    this.cartService.getCartItems().subscribe((cartData) => {
      this.cartItems = cartData;
    });
  }
  trackByFn(index: number, item: CartItem) {
    return item.model; // Assuming you have a unique identifier like 'id'
  }

  orderPlaced(cartItem : CartItem){
    this.cartItems = this.cartItems.filter(item => cartItem !== item);
  }

}
