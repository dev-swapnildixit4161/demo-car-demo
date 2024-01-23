import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { DashboardComponent } from "./dashboard.component";
import { ApiErrorComponent } from "./components/api-error/api-error.component";
import { ServerErrorComponent } from "./components/server-error/server-error.component";

const routes: Routes = [
  {
    path: "",
    component: DashboardComponent,
    children: [
      {
        path: "",
        loadChildren: () =>
          import("../modules/cloud-options/cloud-options.module").then(
            (m) => m.CloudOptionsModule,
          ),
      },
      {
        path: "home",
        loadChildren: () =>
          import("../home/home.module").then((m) => m.HomeModule),
      },
      {
        path: "orders",
        loadChildren: () =>
            import("../modules/orders/orders.module").then((m) => m.OrdersModule)
      },
      {
        path: "api-error",
        component: ApiErrorComponent,
      },
      {
        path: "server-error",
        component: ServerErrorComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DashboardRoutingModule {}
