import { ComponentFixture, TestBed } from "@angular/core/testing";
import { CarBrandsComponent } from "./car-brands.component";
import { CarsListService } from "../services/cars-list.service";
describe("CarBrandsComponent", () => {
  let component: CarBrandsComponent;
  let fixture: ComponentFixture<CarBrandsComponent>;
  let mockCarsListService: jasmine.SpyObj<CarsListService>;

  beforeEach(() => {
    mockCarsListService = jasmine.createSpyObj("CarsListService", [
      "getBrandName",
      "setBrandsName",
    ]);

    TestBed.configureTestingModule({
      declarations: [CarBrandsComponent],
      providers: [{ provide: CarsListService, useValue: mockCarsListService }],
    });

    fixture = TestBed.createComponent(CarBrandsComponent);
    component = fixture.componentInstance;
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should call setBrandsName when a brand is clicked", () => {
    const brandName = "Brand3";

    component.onBrandClick(brandName);

    expect(mockCarsListService.setBrandsName).toHaveBeenCalledWith(brandName);
  });
});
