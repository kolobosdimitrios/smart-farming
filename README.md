# Smart Farming Core Domain

This project implements the domain logic for a smart farming system. It provides classes and use cases for handling sensor readings, irrigation sessions, and crop monitoring. Local in-memory data store implementations are included for quick prototyping.

## Building

Use [Maven](https://maven.apache.org/) to compile and package the library:

```bash
mvn package
```

The build creates `target/smart-farming-core-domain-1.0-SNAPSHOT.jar`.

## Project modules

The code is organized into several packages:

- **application** – use cases and services that orchestrate domain operations.
- **domain** – core domain objects, service interfaces and ports. This now includes a `Field` entity for grouping sensors.
- **infrastructure** – local implementations of repositories that use in-memory storage, consolidated under a single package.


Additional services include a basic soil moisture scheduler that automatically
starts irrigation when the latest moisture reading falls below a configurable
threshold.
