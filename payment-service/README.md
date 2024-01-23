# Payment Service
Payment Service Involves the user verification, and settlement of transactions and on the basis of that executes the PaymentCancelEvent or PaymentApprovedEvent.

## Prerequisites
We must set up Axon Server to handle command and query operations, and configure MySQL as the persistent store destination.

![payment-application-config.png](../documentation/payment-application-config.png)

## Local setup
- Setup axon-server and MySQL docker images
```
local-dev > docker compose up -d
```
- Run the application
```
payment-service > mvn clean springboot:run 
```