# JavaCompentencyDemoUI

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 16.0.2.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.


## Local running Steps
1)  building the docker file from the root folder 
docker build -t us-east1-docker.pkg.dev/shoppertrak-repo/java-competency-demo-ui:latest .

2) Run from local
docker run --env API_URL="https://webapi-dev.appname.com" -dp 8080:80 us-east1-docker.pkg.dev/shoppertrak-repo/java-competency-demo-ui:latest

## Application Functionality

This Angular application primarily deals with car-related operations. It loads car brands and models from the following URLs: 
These are available on GCP and Azure both.

- Private brands URL (GCP): "http://35.193.88.251/v1/data/brands"
- Private brands URL (Azure): "http://40.88.227.104/v1/data/brands"
- Car models URL (Azure): "http://40.88.227.104/v1/data/cars/"
- Car models URL (GCP): "http://35.193.88.251/v1/data/cars/"

### Adding Items to the Cart
When a user adds an item to the cart, a backend application named "CartApplication," written in Spring Boot, handles the process. The `/add` endpoint is called, and in the backend,
it checks whether the product is available in the inventory database. If the product is found, it is added to the SQL database, which is maintained by the CartApplication.

### Removing Items from the Cart
To remove an item from the cart, the `/remove` endpoint of the CartApplication is called. Item will be removed from the database as well.

### Placing an Order
User can increase and decrease the quantity of the particular item while placing the order.
To place an order, the `/order` endpoint of the OrderApplication is called. When an order is successfully placed, a message is received, and the corresponding item is removed from the cart database.

### Endpoints
- Get Cart Items: `http://localhost:9094/cart/get`
- Place Order: `http://localhost:9090/orders`
- Remove from Cart: `http://localhost:9094/cart/remove`
- Add to Cart: `http://localhost:9094/cart/add`

