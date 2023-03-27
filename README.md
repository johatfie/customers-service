# Widgets Are Us Customers microservice

Provides customers information for Widgets Are Us microservices project.

## Technologies
- Java 17
- Spring Boot 2.7.10
- Spring Cloud 2021.0.6
- Spring Data JPA
- Docker
- PostgreSQL
- H2
- Logstash
- Netflix Eureka Client
- RabbitMQ

## Details

- Uses RabbitMQ to receive configuration updates from the config server.
- Registers itself with the Netflix-Eureka service discovery service
- Logging is handled by an ElasticSearch, Logstash, and Kibana (ELK) stack
- Zipkin is used for visualizing user transactions across multiple services
