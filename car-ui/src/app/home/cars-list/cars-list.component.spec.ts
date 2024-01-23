// import { ComponentFixture, TestBed } from "@angular/core/testing";
// import { CarsListComponent } from "./cars-list.component";
// import { CarsListService } from "../services/cars-list.service";
// import { of } from "rxjs";
//
// describe("CarsListComponent", () => {
//   let component: CarsListComponent;
//   let fixture: ComponentFixture<CarsListComponent>;
//   let carsDataServiceMock: Partial<CarsListService>;
//
//   beforeEach(() => {
//     carsDataServiceMock = {
//       getBrandsName: of("Test Brand"),
//       getCarModels: (brandName: string) =>
//         of([{ model: "Car Model 1" }, { model: "Car Model 2" }]),
//     };
//
//     TestBed.configureTestingModule({
//       declarations: [CarsListComponent],
//       providers: [{ provide: CarsListService, useValue: carsDataServiceMock }],
//     }).compileComponents();
//   });
//
//   beforeEach(() => {
//     fixture = TestBed.createComponent(CarsListComponent);
//     component = fixture.componentInstance;
//     fixture.detectChanges();
//   });
//
//   it("should create", () => {
//     expect(component).toBeTruthy();
//   });
//
//   it("should initialize component properties", () => {
//     expect(component.brandName).toEqual("");
//     expect(component.showcarList).toBeFalse();
//     expect(component.selectedCarBrand).toEqual("");
//     expect(component.page).toEqual(1);
//     expect(component.count).toEqual(0);
//     expect(component.tableSize).toEqual(10);
//     expect(component.cars).toBeUndefined();
//     //expect(component.dataSubscription).toBeUndefined();
//   });
//
//   it("should subscribe to getBrandsName and call getCarModels on ngOnInit", () => {
//     component.ngOnInit();
//
//     expect(component.selectedCarBrand).toEqual("Test Brand");
//     expect(component.showcarList).toBeTrue();
//     expect(component.cars).toEqual([
//       { model: "Car Model 1" },
//       { model: "Car Model 2" },
//     ]);
//   });
//
//   it("should call getCarModels on onTableDataChange", () => {
//     const getCarModelsSpy = spyOn(component, "getCarModels");
//
//     const eventData = { itemsPerPage: 10, page: 2 };
//     component.onTableDataChange(eventData);
//
//     expect(getCarModelsSpy).toHaveBeenCalledWith(component.selectedCarBrand);
//   });
//
//   it("should unsubscribe from dataSubscription on ngOnDestroy", () => {
//     const unsubscribeSpy = spyOn(component.dataSubscription, "unsubscribe");
//     component.ngOnDestroy();
//
//     expect(unsubscribeSpy).toHaveBeenCalled();
//   });
// });
