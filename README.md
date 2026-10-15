# RESTful TODO App

A simple Spring Boot REST API for managing tasks (create, read, update, delete, filter by status).

## Tech Stack

| What | Used |
|---|---|
| Framework | Spring Boot (Web, Data JPA, Validation) |
| Database | H2 in-memory (`jdbc:h2:mem:tododb`) |
| ORM | Hibernate / JPA (`ddl-auto=update`) |
| Build | Maven (via Maven Wrapper) |
| Helpers | Lombok, Jakarta Bean Validation |

## Features

- Create, view, update and delete tasks
- Filter tasks by completion status
- Task priority: `LOW`, `MEDIUM` (default), `HIGH`
- Input validation with clear error messages
- Consistent JSON error responses (400 / 404)
- H2 web console for inspecting data

## API

Base URL: `http://localhost:8080/api/v1/tasks`

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/` | Create a task (201) |
| `GET` | `/` | List all tasks |
| `GET` | `/?completed=true` | List tasks filtered by status |
| `GET` | `/{id}` | Get a task by id |
| `PUT` | `/{id}` | Update a task |
| `DELETE` | `/{id}` | Delete a task (204) |

**Request body (POST / PUT)**

```json
{
  "title": "Buy milk",
  "description": "2 liters",
  "dueDate": "2026-10-20",
  "priority": "HIGH"
}
```

**Validation rules**

- `title`: required, max 100 characters
- `description`: max 500 characters
- `dueDate`: today or a future date
- `priority`: optional, defaults to `MEDIUM`

**Error response**

```json
{
  "timestamp": "2026-10-16T12:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Task not found with id 99",
  "path": "/api/v1/tasks/99"
}
```

## Project Structure

Base package: `com.advertismnt.restfultodoapp`

| Package | Contents | Purpose |
|---|---|---|
| `controller` | `TaskController` | REST endpoints |
| `service` | `TaskService`, `impl/TaskServiceImpl` | Business logic, DTO mapping |
| `repository` | `TaskRepository` | Database access (Spring Data JPA) |
| `model` | `Task`, `Priority` | JPA entity and enum |
| `dto` | `TaskRequestDto`, `TaskResponseDto` | Request/response payloads (records) |
| `exception` | `GlobalExceptionHandler`, `ResourceNotFoundException`, `ErrorResponse` | Centralized error handling |
| root | `RestfulTodoApplication` | App entry point |

Config lives in `src/main/resources/application.properties`.

## Run

```bash
./mvnw spring-boot:run      # Linux / macOS
mvnw.cmd spring-boot:run    # Windows
```

- API: `http://localhost:8080/api/v1/tasks`
- H2 console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:tododb`)

> Data is stored in memory and is wiped every time the app restarts.

## Test

```bash
./mvnw test
```
