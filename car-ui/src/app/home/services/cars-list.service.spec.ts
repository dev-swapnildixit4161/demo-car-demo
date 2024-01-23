import { TestBed } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import { CarsListService } from "./cars-list.service";

describe("CarsListService", () => {
  let service: CarsListService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CarsListService],
    });

    service = TestBed.inject(CarsListService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it("should be created", () => {
    expect(service).toBeTruthy();
  });

  // it("should call the correct API to fetch data with the given page number", () => {
  //   const pageNumber = 1;
  //   const mockResponse = [{ id: 1, name: "Car A" }];
  //
  //   service.getData(pageNumber).subscribe((data) => {
  //     expect(data).toEqual(mockResponse);
  //   });
  //
  //   const req = httpMock.expectOne(
  //     `${service["apiUrl"]}&page_number=${pageNumber}`,
  //   );
  //   expect(req.request.method).toBe("GET");
  //   req.flush(mockResponse);
  // });

  // it("should set and get brands' names correctly", () => {
  //   const testBrandsName = "Brand1";
  //
  //   service.setBrandsName(testBrandsName);
  //   service.getBrandsName.subscribe((brandsName) => {
  //     expect(brandsName).toBe(testBrandsName);
  //   });
  // });

  // it("should fetch the brand names from the mock API", () => {
  //   const mockBrandNames: CarsDetails[] = [
  //     {
  //       brand_id: 1,
  //       brand_name: "Brand1",
  //       model: "Model1",
  //       year: 2022,
  //       color: "Blue",
  //       mileage: 5000,
  //       price: "$25000",
  //       location: "Location1",
  //     },
  //     // Add more mock brand data as needed
  //   ];
  //
  //   service.getBrandName().subscribe((data) => {
  //     expect(data).toEqual(mockBrandNames);
  //   });
  //
  //   const req = httpMock.expectOne(service["apiUrlBrand"]);
  //   expect(req.request.method).toBe("GET");
  //   req.flush(mockBrandNames);
  // });
});
