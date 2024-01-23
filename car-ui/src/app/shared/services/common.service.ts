import { Injectable } from "@angular/core";
import { ColDef } from "ag-grid-community";

@Injectable({
  providedIn: "root",
})
export class CommonService {
  constructor() {}
  setTableHeaders(event: any, tableHeaders: ColDef[]): ColDef<any>[] {
    let updatedHeaders: ColDef<any>[] = [];
    if (event.length > 0) {
      tableHeaders.forEach((item: ColDef) => {
        if (event.includes(item.colId)) {
          updatedHeaders.push(item);
        }
      });
    }
    return updatedHeaders;
  }
}
