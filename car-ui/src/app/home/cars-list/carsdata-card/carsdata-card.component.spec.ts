import { ComponentFixture, TestBed } from "@angular/core/testing";
import { CarsdataCardComponent } from "./carsdata-card.component";
import { MatCardModule } from "@angular/material/card";
import { Component } from "@angular/core"; // Import the MatCardModule

describe("CarsdataCardComponent", () => {
  let component: CarsdataCardComponent;
  let fixture: ComponentFixture<CarsdataCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarsdataCardComponent],
      imports: [MatCardModule], // Include the MatCardModule in the imports array
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarsdataCardComponent);
    component = fixture.componentInstance;
  });

  it("should create the component", () => {
    expect(component).toBeTruthy();
  });

  it("should update car data when input properties change", () => {
    // Set initial values
    component.carCompanyName = "Ford";
    component.carModel = "Mustang";
    component.carPrice = "35000";
    component.carMileage = "25";
    component.manufacturingYear = "2023";
    component.carLocation = "Chicago";
    component.carColor = "Black";

    fixture.detectChanges();

    let ComponentElement: HTMLElement = fixture.nativeElement;
    expect(ComponentElement.textContent).toContain("Ford");
    expect(ComponentElement.textContent).toContain("Mustang");
    expect(ComponentElement.textContent).toContain("35000");
    expect(ComponentElement.textContent).toContain("25");
    expect(ComponentElement.textContent).toContain("2023");
    expect(ComponentElement.textContent).toContain("Chicago");
    expect(ComponentElement.textContent).toContain("Black");

    // Update input properties
    component.carCompanyName = "Chevrolet";
    component.carModel = "Camero";
    component.carPrice = "30000";
    component.carMileage = "28";
    component.manufacturingYear = "2022";
    component.carLocation = "Miami";
    component.carColor = "Silver";

    fixture.detectChanges();

    ComponentElement = fixture.nativeElement;
    expect(ComponentElement.textContent).toContain("Chevrolet");
    expect(ComponentElement.textContent).toContain("Camero");
    expect(ComponentElement.textContent).toContain("30000");
    expect(ComponentElement.textContent).toContain("28");
    expect(ComponentElement.textContent).toContain("2022");
    expect(ComponentElement.textContent).toContain("Miami");
    expect(ComponentElement.textContent).toContain("Silver");
  });
});
