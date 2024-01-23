import { Component } from "@angular/core";
import { ICellRendererAngularComp } from "ag-grid-angular";
import { ICellRendererParams } from "ag-grid-community";
import { CartService} from "../../services/cart.service";
import { HttpClient } from "@angular/common/http";
import { MatSnackBar } from "@angular/material/snack-bar";

@Component({
  selector: "app-action-column",
  templateUrl: "./action-column.component.html",
  styleUrls: ["./action-column.component.scss"],
})
export class ActionColumnComponent implements ICellRendererAngularComp {
  constructor(
    private cartService: CartService,
    private httpClient: HttpClient,
    private snackBar: MatSnackBar,
  ) {}

  public bulkEventCellValue!: any;
  public carId!: string;

  agInit(params: ICellRendererParams): void {
    this.bulkEventCellValue = params;
    this.carId = params.data.carId;
  }

  refresh(params: ICellRendererParams) {
    this.bulkEventCellValue = params;
    return true;
  }

  addToCart(): void {
    this.cartService.addToCart(this.carId).subscribe((response) => {
      if (response != null) {
        this.cartService.incrementCartItemCount();
        this.showSnackBar("Item has been added to the cart");
      } else {
        console.error("Failed to add to cart:");
      }
    });
  }

  private showSnackBar(message: string) {
    this.snackBar.open(message, "Close", {
      duration: 3000,
    });
  }
}
