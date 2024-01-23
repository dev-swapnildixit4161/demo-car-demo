import { Component } from "@angular/core";
import { Router, RoutesRecognized } from "@angular/router";
import { filter, pairwise } from "rxjs";
import { ROUTES, ICONS } from "../../../constants/constants";

/**
 * Component representing the side navigation menu.
 */
@Component({
  selector: "app-sidenav",
  templateUrl: "./sidenav.component.html",
  styleUrls: ["./sidenav.component.scss"],
})
export class SidenavComponent {
  /** Indicates whether the side menu is open or closed. */
  isMenuOpen = true;
  /** The icon used for toggling the side menu. */
  arrowIcon: string = "keyboard_double_arrow_left";
  /** The margin for the content when the side menu is open. */
  contentMargin = 240;
  /** The URL of the previous route. */
  previousUrl: string = ROUTES.DASHBOARD;
  /** The menu items displayed in the side menu. */
  menuItems = [
    {
      path: ROUTES.DASHBOARD,
      icon: ICONS.CLOUD_FUNCTIONS,
      label: "Cloud Functions",
    },
    {
      path: `${ROUTES.DASHBOARD}/home`,
      icon: ICONS.OLD_UI,
      label: "Old UI",
    },
    {
      path: ROUTES.MY_ORDERS,
      icon: ICONS.MY_ORDERS,
      label: "My Orders",
    }
  ];

  /**
   * Creates an instance of SidenavComponent.
   * @param {Router} router - The Angular router for navigation.
   */
  constructor(private router: Router) {}

  /**
   * Initializes the component and subscribes to route change events.
   */
  ngOnInit() {
    this.router.events
      .pipe(
        filter((event: any) => event instanceof RoutesRecognized),
        pairwise(),
      )
      .subscribe((events: RoutesRecognized[]) => {
        this.previousUrl = events[0].urlAfterRedirects;
      });
  }

  /**
   * Checks if the given route is active.
   * @param {string} url - The URL to check against the current route.
   * @returns {boolean} - True if the route is active, otherwise false.
   */
  checkRouteActive(url: string): boolean {
    let routerUrl = this.router.url;

    if (routerUrl.includes("?")) {
      routerUrl = this.router.url.substring(0, routerUrl.indexOf("?"));
    }
    if (this.previousUrl.includes("?")) {
      this.previousUrl = this.previousUrl.substring(
        0,
        this.previousUrl.indexOf("?"),
      );
    }

    if (url === routerUrl) {
      return true;
    }
    if (url === "/dashboard" && routerUrl.includes("/dashboard/gcp")) {
      return true;
    }

    if (
      url === "/dashboard/home" &&
      (routerUrl.includes("/dashboard/home/gcp") ||
        routerUrl.includes("/dashboard/home/azure"))
    ) {
      return true;
    }

    if (
      this.router.url === "/dashboard/api-error" &&
      this.previousUrl !== undefined
    ) {
      if (this.previousUrl === url) {
        return true;
      }
    }
    return false;
  }

  /**
   * Toggles the side menu's open/close state and adjusts the content margin accordingly.
   */
  onToolbarMenuToggle() {
    this.isMenuOpen = !this.isMenuOpen;
    this.arrowIcon = !this.isMenuOpen
      ? "keyboard_double_arrow_right"
      : "keyboard_double_arrow_left";
    if (!this.isMenuOpen) {
      this.contentMargin = 70;
    } else {
      this.contentMargin = 240;
    }
  }
}
