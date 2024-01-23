import {Component, Input, OnInit} from "@angular/core";
import {Router} from "@angular/router";

@Component({
  selector: "app-carsdata-card",
  templateUrl: "./carsdata-card.component.html",
  styleUrls: ["./carsdata-card.component.scss"],
})
export class CarsdataCardComponent implements OnInit{

  isNewUI: boolean = true;
  constructor(private router: Router) {}


  /**
   * Holds the name of the car's manufacturing company.
   * This property is passed as an input to the component and displayed in the template.
   */
  @Input() carCompanyName: string = "";

  /**
   * Holds the model name of the car.
   * This property is passed as an input to the component and displayed in the template.
   */
  @Input() carModel: string = "";

  /**
   * Holds the price of the car.
   * This property is passed as an input to the component and displayed in the template.
   */
  @Input() carPrice: string = "";

  /**
   * Holds the mileage information of the car.
   * This property is passed as an input to the component and displayed in the template.
   */
  @Input() carMileage!: number;

  /**
   * Holds the manufacturing year of the car.
   * This property is passed as an input to the component and displayed in the template.
   */
  @Input() manufacturingYear!: number;

  /**
   * Holds the location information of the car.
   * This property is passed as an input to the component and displayed in the template.
   */
  @Input() carLocation: string = "";

  /**
   * Holds the color of the car.
   * This property is passed as an input to the component and displayed in the template.
   */
  @Input() carColor: string = "";

  ngOnInit() {
    if (this.router.url.includes("dashboard")||this.router.url.includes("search")) {
      this.isNewUI = false;
    }
  }
}
