import {Component, EventEmitter, Input, Output} from "@angular/core";
import { CartItem } from "../../../shared/module/cart-item.model";
import {CartService} from "../../../shared/services/cart.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: "app-cart-items",
  templateUrl: "./cart-items.component.html",
  styleUrls: ["./cart-items.component.scss"],
})
export class CartItemsComponent {
  @Input() cartItem!: CartItem;
  @Output() OrderPlaced = new EventEmitter<CartItem>();

  constructor(private cartService : CartService, private snackBar : MatSnackBar) {
  }

  placeOrder(productId: string, quantity: number){
    this.cartService.makeOrder({
      productId: productId,
      quantity: quantity,
      userId: '1652'
    }).subscribe({
      next: (orderSummary) => {
        this.snackBar.open('Order placed successfully', 'Close' , {duration : 2000});
        this.OrderPlaced.emit(this.cartItem);
        this.removeFromDatabase(productId, quantity, '1652');
        console.log('Order placed successfully:', orderSummary);
      },
      error: (error) => {
        this.snackBar.open('Error placing order:', 'Close' , {duration : 2000});
        console.error('Error placing order:', error);
      }
    });
  }

  removeFromDatabase(productId: string, quantity: number, userId: string) {
    this.cartService.removeFromCart(productId, quantity, userId).subscribe(
        {next : (response) => {
          this.cartService.decrementCartItemCount(quantity);
        console.log('Item removed from database:', response);
      },
     error : (error) => {
        console.error('Error removing item from database:', error);
      }
    });
  }
  increaseQuantity(cartItem: CartItem) {
    this.cartService.addToCart(cartItem.productId).subscribe(
        (response) => {
          cartItem.quantity += 1;
          this.cartService.incrementCartItemCount();
          this.snackBar.open('Quantity updated successfully', 'Close', { duration: 2000 });
        },
        (error) => {
          console.error('Error updating quantity:', error);
        }
    );
  }

    decreaseQuantity(cartItem: CartItem) {
        this.cartService.removeFromCart(cartItem.productId, 1, '1652').subscribe(
            (response) => {
                if (Number(cartItem.quantity) == 1) {
                    this.OrderPlaced.emit(this.cartItem);
                    this.removeFromDatabase(cartItem.productId, 1, '1652');
                } else {
                    (cartItem as any).quantity -= 1;
                    this.cartService.decrementCartItemCount(1);
                }
                this.snackBar.open('Item removed from cart', 'Close', { duration: 2000 });
            },
            (error) => {
                console.error('Error removing item from cart:', error);
            }
        );
    }

  protected readonly Number = Number;
}
