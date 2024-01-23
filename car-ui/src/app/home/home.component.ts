import { Component, OnInit } from "@angular/core";
import { constants } from "../constants/constants";
import { AvailableCloudOptions } from "../shared/module/cloud-item-details.model";
import { Router } from "@angular/router";

/**
 * Represents the HomeComponent, the landing page of the application.
 */
@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {
  isHeaderVisible: boolean = true;
  Title: string = "Java UI Demo";
  Description: string = "A Reactive Application";

  /**
   * The URL for the NashTech logo, obtained from the constants file.
   */
  nashTechLogoUrl: string = constants.nashTechLogoUrl;

  /**
   * An array of cloud options with their labels and paths.
   */
  cloudOptions: AvailableCloudOptions[] = [
    {
      label: "Azure",
      path: "./azure",
      icon: constants.azureIcon,
      color: "accent",
      isSelected: false,
    },
    {
      label: "GCP",
      path: "./gcp",
      icon: constants.gcpIcon,
      color: "accent",
      isSelected: true,
    },
  ];
  constructor(private router: Router) {}

  ngOnInit() {
    if (this.router.url.includes("dashboard")) {
      this.isHeaderVisible = false;
    }
  }
}
