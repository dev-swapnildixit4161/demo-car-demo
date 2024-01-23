import { Component } from "@angular/core";
import { IsActiveMatchOptions } from "@angular/router";

@Component({
  selector: "app-tab-bar",
  templateUrl: "./tab-bar.component.html",
  styleUrls: ["./tab-bar.component.scss"],
})
export class TabBarComponent {
  matchOptions: IsActiveMatchOptions = {
    paths: "exact",
    matrixParams: "exact",
    queryParams: "subset",
    fragment: "ignored",
  };
  shrinkTabs: { id: number; tabName: string; url: string }[] = [
    { id: 0, tabName: "Azure", url: "/dashboard" },
    { id: 1, tabName: "GCP", url: "/dashboard/gcp" },
  ];
}
