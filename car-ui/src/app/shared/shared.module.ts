import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { TabularViewComponent } from "./components/tabular-view/tabular-view.component";
import { MaterialModule } from "./module/material.module";
import { AgGridModule } from "ag-grid-angular";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ActionColumnComponent } from "./components/action-column/action-column.component";
import { TableHeadersDropdownComponent } from "./components/table-headers-dropdown/table-headers-dropdown.component";
import { DataNotFoundComponent } from "./components/data-not-found/data-not-found.component";

@NgModule({
  declarations: [
    TabularViewComponent,
    ActionColumnComponent,
    TableHeadersDropdownComponent,
    DataNotFoundComponent,
  ],
  imports: [
    CommonModule,
    MaterialModule,
    AgGridModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [
    TabularViewComponent,
    ActionColumnComponent,
    TableHeadersDropdownComponent,
    FormsModule,
    ReactiveFormsModule,
    DataNotFoundComponent,
  ],
})
export class SharedModule {}
