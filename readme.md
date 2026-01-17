# Jari - Jira Clone Microservices Architecture

A microservices-based project management system inspired by Jira, built with Spring Boot and Spring Cloud.

## Architecture Overview

This project follows a microservices architecture pattern with the following components:

### Services

1. **Eureka Discovery Service** (Port: 8761)

   - Service registry and discovery server
   - Enables microservices to find and communicate with each other

2. **API Gateway** (Port: 8080)

   - Single entry point for all client requests
   - Routes requests to appropriate microservices
   - Built with Spring Cloud Gateway

3. **User Service** (Port: 8081)

   - User management and authentication
   - Handles user CRUD operations
   - Database: `jari_user`

4. **Project Service** (Port: 8082)

   - Project management
   - Handles project CRUD operations
   - Database: `jari_project`

5. **Task Service** (Port: 8083)

   - Task/Issue management
   - Handles task CRUD operations
   - Database: `jari_task`

6. **Notification Service** (Port: 8084)
   - Notification management
   - Handles user notifications
   - Database: `jari_notification`

### Common Module

- Shared DTOs (BaseDto, ResponseDto, ErrorResponseDto)
- Common exceptions and exception handlers
- Shared utilities

## Technology Stack

- **Java**: 17
- **Spring Boot**: 3.2.4
- **Spring Cloud**: 2023.0.1
- **Spring Cloud Gateway**: API Gateway
- **Netflix Eureka**: Service Discovery
- **Spring Data JPA**: Data persistence
- **MySQL**: Database
- **Maven**: Build tool
- **Docker & Docker Compose**: Containerization

## Project Structure

```
jari/
├── pom.xml                          # Parent POM
├── compose.yml                      # Docker Compose configuration
├── Dockerfile                       # Multi-stage Dockerfile
├── jari-common/                     # Common module
│   ├── pom.xml
│   └── src/main/java/com/example/jari/common/
├── jari-discovery/                  # Eureka Discovery Service
│   ├── pom.xml
│   └── src/main/java/com/example/jari/discovery/
├── jari-gateway/                    # API Gateway
│   ├── pom.xml
│   └── src/main/java/com/example/jari/gateway/
├── jari-user-service/               # User Service
│   ├── pom.xml
│   └── src/main/java/com/example/jari/user/
├── jari-project-service/            # Project Service
│   ├── pom.xml
│   └── src/main/java/com/example/jari/project/
├── jari-task-service/               # Task Service
│   ├── pom.xml
│   └── src/main/java/com/example/jari/task/
└── jari-notification-service/       # Notification Service
    ├── pom.xml
    └── src/main/java/com/example/jari/notification/
```

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Docker and Docker Compose (optional, for containerized deployment)

## Getting Started

### Local Development

1. **Start MySQL Database**

   ```bash
   docker-compose up mysql -d
   ```

   Or use your local MySQL instance and update the connection strings in `application.yml` files.

2. **Build the Project**

   ```bash
   mvn clean install
   ```

3. **Start Services in Order**

   Start Discovery Service first:

   ```bash
   cd jari-discovery
   mvn spring-boot:run
   ```

   Start API Gateway:

   ```bash
   cd jari-gateway
   mvn spring-boot:run
   ```

   Start User Service:

   ```bash
   cd jari-user-service
   mvn spring-boot:run
   ```

   Start Project Service:

   ```bash
   cd jari-project-service
   mvn spring-boot:run
   ```

   Start Task Service:

   ```bash
   cd jari-task-service
   mvn spring-boot:run
   ```

   Start Notification Service:

   ```bash
   cd jari-notification-service
   mvn spring-boot:run
   ```

### Docker Deployment

1. **Build and Start All Services**

   ```bash
   docker-compose up --build
   ```

2. **Start Services in Detached Mode**

   ```bash
   docker-compose up -d
   ```

3. **View Logs**

   ```bash
   docker-compose logs -f [service-name]
   ```

4. **Stop Services**
   ```bash
   docker-compose down
   ```

## API Endpoints

All requests should go through the API Gateway at `http://localhost:8080`

### User Service

- `POST /api/users` - Create user
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users` - Get all users
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Project Service

- `POST /api/projects` - Create project
- `GET /api/projects/{id}` - Get project by ID
- `GET /api/projects/key/{key}` - Get project by key
- `GET /api/projects` - Get all projects
- `PUT /api/projects/{id}` - Update project
- `DELETE /api/projects/{id}` - Delete project

### Task Service

- `POST /api/tasks` - Create task
- `GET /api/tasks/{id}` - Get task by ID
- `GET /api/tasks/key/{key}` - Get task by key
- `GET /api/tasks` - Get all tasks
- `GET /api/tasks/project/{projectId}` - Get tasks by project
- `GET /api/tasks/assignee/{assigneeId}` - Get tasks by assignee
- `PUT /api/tasks/{id}` - Update task
- `DELETE /api/tasks/{id}` - Delete task

### Notification Service

- `POST /api/notifications` - Create notification
- `GET /api/notifications/{id}` - Get notification by ID
- `GET /api/notifications/user/{userId}` - Get notifications by user
- `GET /api/notifications/user/{userId}/unread` - Get unread notifications
- `GET /api/notifications/user/{userId}/unread/count` - Get unread count
- `PUT /api/notifications/{id}/read` - Mark as read
- `PUT /api/notifications/user/{userId}/read-all` - Mark all as read
- `DELETE /api/notifications/{id}` - Delete notification

## Service Discovery

- Eureka Dashboard: `http://localhost:8761`
- View all registered services and their status

## Database Configuration

Each microservice uses its own database:

- `jari_user` - User Service
- `jari_project` - Project Service
- `jari_task` - Task Service
- `jari_notification` - Notification Service

Databases are automatically created if they don't exist (via `createDatabaseIfNotExist=true`).

## Development Notes

- Each service runs on a different port to avoid conflicts
- Services communicate through Eureka service discovery
- API Gateway routes requests using service names (e.g., `lb://jari-user-service`)
- Common module is shared across all services for consistency

## Future Enhancements

- [ ] Add authentication and authorization (JWT)
- [ ] Implement inter-service communication (Feign Client)
- [ ] Add API documentation (Swagger/OpenAPI)
- [ ] Implement distributed tracing (Zipkin/Sleuth)
- [ ] Add message queue for async communication (RabbitMQ/Kafka)
- [ ] Implement caching (Redis)
- [ ] Add monitoring and metrics (Prometheus/Grafana)
- [ ] Implement CI/CD pipeline
- [ ] Add comprehensive test coverage

## License

This project is for educational purposes.
