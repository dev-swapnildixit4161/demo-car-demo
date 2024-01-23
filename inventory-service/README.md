# Inventory Service

##### Application Overview

This application is conscientiously crafted to oversee the management of available cars for sale within a system. Leveraging the Axon Framework for CQRS (Command Query Responsibility Segregation) and Event Sourcing, it seamlessly orchestrates the creation of inventory, ensures the storage of product details in a database, and adeptly publishes events to a Google Cloud Pub/Sub topic.

#### Workflow

### Inventory Creation

- It all starts with a CreateProductCommand, managed by the Inventory Aggregate class. 
- The aggregate takes care of the command, leading to the triggering of the ProductCreatedEvent.

### Event Handling

The ProductEventHandler takes care of the ProductCreatedEvent.

- It grabs the product information from the event. 
- The details are then stored in a database through the ProductRepository. 
- On top of that, the PubSubPublisherService is employed to broadcast the product details to a Google Cloud Pub/Sub topic.

### Google Cloud Pub/Sub Integration:

- The PubSubPublisherService sets up the Pub/Sub publisher when the application starts using @PostConstruct. 
- It handles turning product data into a Pub/Sub message and sends it to the specified topic. 
- When the application shuts down with @PreDestroy, the publisher is smoothly stopped.

### Data Storage

- Product details are persisted to a database for future reference. 
- The ProductRepository handles the database interactions.

##### Key Components

### InventoryAggregate



Manages all aspects of product creation, monitors ongoing activities, and stays informed through events to ensure up-to-date awareness of the process.

### ProductEventHandler


Listens for the ProductCreatedEvent, grabs product info from the event, stores it in a database through the ProductRepository, and spreads the word about the product by sharing details on Google Cloud Pub/Sub using the PubSubPublisherService.

### PubSubPublisherService

Sets up a Google Cloud Pub/Sub publisher when the application starts, sends product information to a Pub/Sub topic, and ensures a smooth shutdown for the publisher when the application closes.

