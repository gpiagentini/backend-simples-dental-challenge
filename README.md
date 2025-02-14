# Spring Boot Hexagonal Architecture Template

This is a template project for Spring Boot applications designed using **Hexagonal Architecture** (Ports and Adapters). It helps you structure your codebase to be maintainable, scalable, and flexible, following Domain-Driven Design (DDD) principles. This template provides clear separation of concerns between different layers, ensuring that your business logic is not tightly coupled to any framework or infrastructure.

## Project Overview

- **Framework**: Spring Boot 3.x
- **Java Version**: 21
- **Build Tool**: Maven
- **Architecture**: Hexagonal Architecture (Ports & Adapters)

## Project Structure

```text
src/
 └── main/
     ├── java/
     │   └── br/com/gpiagentini/api/
     │       ├── application/           # Application layer (use cases)
     │       │   ├── service/           # Application services
     │       │   └── port/              # Input and output ports (interfaces)
     │       ├── domain/                # Domain layer (core business logic)
     │       ├── infrastructure/        # Infrastructure layer (adapters, controllers, etc.)
     │       │   ├── adapters/          # Controllers, persistence adapters, etc.
     │       │   └── persistence/       # Repositories and data mappers
     └── resources/
         ├── application.yml            # Spring Boot configuration
````

## Managing Environments

In this template, we have two possible environments (`dev` and `prod`).
To alternate between our environments, we need to use the **environment variable** **SPRING_PROFILES_ACTIVE**.

> [!NOTE]
> The following commands will only change your environment variable for the **current terminal** session. It's not a permanent approach.

### Linux

cmd
```
export SPRING_PROFILES_ACTIVE=<env>
```

### Windows

cmd
```
set SPRING_PROFILES_ACTIVE=<env>
```

Powershell
```
$env:SPRING_PROFILES_ACTIVE="<env>"
```
