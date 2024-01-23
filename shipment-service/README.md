## Shipment Service

#### Application Overview
This application is designed to manage the shipment process in a system using the Axon Framework for CQRS (Command Query Responsibility Segregation) and Event Sourcing. It includes components for handling the creation of shipments, persisting shipment details in a database, and publishing events to a Google Cloud Pub/Sub topic.

## Workflow

### Shipment Creation:
* The process begins with a CreateShipmentCommand, which is handled by the ShipmentAggregate class. 
* The aggregate processes the command, applies the ShipmentCreatedEvent, and triggers the OrderShippedEvent.

### Event Handling:
* The ShipmentCreatedEvent is handled by the ShipmentEventHandler.
* The event handler extracts user information and shipment details from the event.
* It saves the shipment details to a database using the ShipmentRepository.
* Additionally, it uses the PubSubPublisherService to publish the shipment details to a Google Cloud Pub/Sub topic.

### Google Cloud Pub/Sub Integration:
* The PubSubPublisherService initializes the Pub/Sub publisher during application startup.
* The publisher is responsible for converting shipment data into a Pub/Sub message and publishing it to the configured topic.
* During application shutdown the publisher is gracefully shut down.

### Data Storage:
* Shipment details, including user information, are persisted to a database for future reference.
* The ShipmentRepository handles the database interactions.

## Key Components

### ShipmentAggregate:

Manages the state and behavior related to the creation of shipments.
Applies events to update its internal state.

### ShipmentEventHandler:
Listens for the ShipmentCreatedEvent.
    Extracts shipment and user details from the event.
    Saves shipment details to a database using the ShipmentRepository.
    Publishes shipment details to Google Cloud Pub/Sub using the PubSubPublisherService.

### PubSubPublisherService:
Initializes a Google Cloud Pub/Sub publisher during application startup.
    Publishes shipment data to a Pub/Sub topic.
    Gracefully shuts down the publisher during application shutdown.