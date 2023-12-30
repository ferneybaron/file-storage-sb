# File Storage Micro-Service

This is a demo Spring Boot project for upload large files and storage service

## Key Features

- Upload Chunk files
- Combine Chunks

## Architecture

This application is designed based on the Hexagonal Architecture, also known as Ports and Adapters architecture. The main idea behind this architecture is to decouple the core business logic of the application from external dependencies, such as databases, UI frameworks, and other external systems.

Firstly, we define the principles for dividing our code. The hexagonal architecture defines the internal and external parts.

What we did was divide our application into three layers: Web (external), core (internal), and data-source (external).

![architecture](docs/img/hexagonal-architecture.png)

## Core Layer

The `core` module contains the core components of the application.

* Model: This folder holds the domain models or entities of the application. These models represent the core business concepts and encapsulate the data and behavior related to them.
* Domain: This folder contains domain services that encapsulate complex business logic not fitting naturally within a specific entity. Domain services implement the business logic associated with each use case and orchestrate the interactions between the domain models, services, and repositories.
* Service: This folder represents the application's use cases. The Use cases define the specific actions or tasks that the application can perform to fulfill user requirements. Each use case typically corresponds to a specific user goal or action.
* Repo: The repositories layer provides an interface for interacting with the underlying data storage. They typically encapsulate operations related to CRUD (Create, Read, Update, Delete) operations.

## Web Layer

The `Web` module is responsible for handling the communication and interaction between the application and external systems or user interfaces.

## Data Source Layer

The `data` module is responsible for managing the data persistence and external service integration aspects of the application.


### Prerequisites

-[Java SE Development Kit 17.0.9](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) - The software that provides development tools for creating Java programs.

-[Gradle](https://gradle.org/install) - Is a build automation tool for multi-language software development.

-[Git](https://gitforwindows.org/) -  It is a distributed version control system.

### Clone the GitHub repository from the terminal.

```bash
git clone https://github.com/ferneybaron/file-storage-sb.git
```

### Build the Application

```bash
./gradlew build
```

### Run the Application
```bash
./gradlew bootRun
```

## License

This project is licensed under the [MIT License](LICENSE). See the [LICENSE](LICENSE) file for details.