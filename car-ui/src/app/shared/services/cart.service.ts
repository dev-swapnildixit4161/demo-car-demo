import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { CartItem } from "../module/cart-item.model";
import {BehaviorSubject, map, Observable} from "rxjs";
import {OrderSummary} from "../module/OrderSummary";

@Injectable({
  providedIn: "root",
})
export class CartService {

  private cartItemCountSubject: BehaviorSubject<number>;
  cartItemCount$: Observable<number>;

  //cart service api
  private getCartItemUrl : string =  "http://localhost:9094/cart/get";
  private removeFromCartUrl :string = "http://localhost:9094/cart/remove";
  private addToCartUrl : string = "http://localhost:9094/cart/add";
  //order service api
  private placeOrderUrl :string  =  "http://localhost:9090/orders";

  private baseSearchUrl : string = "http://localhost:8080/apis/car";

  private getOrdersUrl = "http://localhost:9090/orders";


  constructor(private httpClient: HttpClient) {
    const initialCount = parseInt(localStorage.getItem('cartCount') || '0', 10);
    this.cartItemCountSubject = new BehaviorSubject<number>(initialCount);
    this.cartItemCount$ = this.cartItemCountSubject.asObservable();
  }
  getCartItems(): Observable<CartItem[]> {
    const url = `${this.getCartItemUrl}?userId=1652`;
    return this.httpClient.get<any[]>(url).pipe(
        map(response => {
          if (response != null) {
            return response;
          } else {
            console.error("Failed to get cart items");
            return [];
          }
        })
    );
  }

  makeOrder(orderRequest: any): Observable<OrderSummary> {
    const url = `${this.placeOrderUrl}/create`;
    return this.httpClient.post<OrderSummary>(url, orderRequest);
  }

  incrementCartItemCount(): void {
    const currentCount = this.cartItemCountSubject.value;
    const newCount = currentCount + 1;
    this.cartItemCountSubject.next(newCount);
    localStorage.setItem('cartCount', newCount.toString());
  }

  decrementCartItemCount(quantity : number): void {
    const currentCount = this.cartItemCountSubject.value;
    const newCount = currentCount - quantity;
    this.cartItemCountSubject.next(newCount);
    localStorage.setItem('cartCount', newCount.toString());
  }

  removeFromCart(productId: string, quantity: number, userId: string): Observable<any> {
    const url = this.removeFromCartUrl+`?productId=${productId}&quantity=${quantity}&userId=1652`;
    return this.httpClient.post<any>(url, null);
  }

  addToCart(carId : string): Observable <any> {
    const url = this.addToCartUrl + `?productId=${carId}&quantity=1&userId=1652`;
    return this.httpClient.post<any>(url, null);
  }

  searchAllCars(){
    const url = `${this.baseSearchUrl}/all`;
    return this.httpClient.get <any>(url);
  }

  searchCarsById(carId: string) {
    const url = `${this.baseSearchUrl}/byId/${carId}`;
    return this.httpClient.get<any>(url);
  }

  searchCarsByMileage(mileage: string) {
    const url = `${this.baseSearchUrl}/byMileage/${mileage}`;
    return this.httpClient.get<any>(url);
  }

  searchCarsByBrand(brand: string) {
    const url = `${this.baseSearchUrl}/byBrand/${brand}`;
    return this.httpClient.get<any>(url);
  }

  searchCarsByPrice(price: string) {
    const url = `${this.baseSearchUrl}/byPrice/${price}`;
    return this.httpClient.get<any>(url);
  }

  getAllOrders(){
    const url = `${this.getOrdersUrl}/1652`;
    return this.httpClient.get <any>(url);
  }

}
