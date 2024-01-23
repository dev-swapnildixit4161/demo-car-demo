# Elastic search service

This service listens to events from cloud events from Azure Eventhub/Gcp Pubsub. The event is then saved to the Elasticsearch server. An endpoint is exposed which will fetch the data from Elasticsearch and return back the appropriate response.

## Prerequisites

We need to configure the event source to listen and the target persistent store.

- Azure config
    - spring.kafka
        - bootstrap-servers: <Azure EventHub>
        - properties.sasl.jaas.config: <Azure EventHub Connection string>
    - topic
        - producer: <Eventhub topic name>
![img](https://github.com/NashTech-Labs/car-demo/assets/102946997/01e44029-a98e-4eb8-ace1-804dde79c174)

- GCP config
    - pubsub
        - topic: <Topic name>
        - subscriptionId
        - projected
        - credentials
    - elastic
        - hostname
        - port
![img_1](https://github.com/NashTech-Labs/car-demo/assets/102946997/12c42a15-5710-4a07-a2df-ef93e5e2d1ea)

## Local setup

- Setup elastic docker images
```bash
docker compose up -d
```
- Run the application
```bash
mvn springboot:run --Dspring-boot.run.profiles=azure
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
