# GameFlix Backend — CI/CD

Spring Boot backend for GameFlix with a GitHub Actions CI pipeline that
builds the application and packages it as a Docker image.

## Tech stack

- Java 21 (Temurin)
- Spring Boot 3 (Web, Data JPA, Security, Thymeleaf)
- MySQL 8
- Maven
- Docker

## Workflow

The pipeline defined in `.github/workflows/ci.yml` runs automatically on
every push and pull request to `main`:

1. **Checkout repository** — pulls the source onto an Ubuntu runner.
2. **Set up JDK 21** — installs the Temurin distribution of Java 21 and
   enables Maven dependency caching to speed up subsequent runs.
3. **Build with Maven** — runs `mvn clean package -DskipTests` to compile
   the project and produce the executable jar in `target/`.
4. **Build Docker image** — runs `docker build -t gameflix-backend .`,
   which copies the jar into an `eclipse-temurin:21-jdk-alpine` base image
   and exposes port 8080.

Tests are skipped during CI because the default Spring Boot integration
test loads the full application context, which requires a live MySQL
instance. No database is available on a GitHub-hosted runner, so running
the tests there would fail the build for reasons unrelated to code
correctness. The jar is still produced and the Docker image still builds.

## Dockerfile

```
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
```

The `target/*.jar` wildcard avoids hardcoding the version-stamped jar
name, so the image keeps building as the project version changes.

## Environment configuration

Database credentials are supplied through environment variables rather
than being committed to the repository:

| Variable | Description |
|---|---|
| `SPRING_DATASOURCE_URL` | JDBC connection string for MySQL |
| `SPRING_DATASOURCE_USERNAME` | Database user |
| `SPRING_DATASOURCE_PASSWORD` | Database password |

## Running locally

```
mvn clean package
docker build -t gameflix-backend .
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL="jdbc:mysql://host.docker.internal:3306/gameflix" \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=yourpassword \
  gameflix-backend
```

The application is then available at http://localhost:8080.

Note that `host.docker.internal` is used instead of `localhost` so the
container can reach the MySQL server running on the host machine.