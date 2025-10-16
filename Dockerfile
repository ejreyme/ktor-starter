# Stage 1: Build the app
FROM gradle:9-jdk24 AS builder
WORKDIR /app
# Copy only Gradle configuration files first to leverage caching
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY gradle ./gradle
# Download dependencies to cache them
RUN gradle --no-daemon dependencies
# Copy the rest of the application code
COPY src ./src
# Build the app with parallel execution and no daemon
RUN gradle installDist --no-daemon --parallel --stacktrace

# Stage 2: Run the app
FROM eclipse-temurin:24-jre-alpine
WORKDIR /app
# Copy only the necessary build output
COPY --from=builder /app/build/install/ktor-starter/bin ./bin
COPY --from=builder /app/build/install/ktor-starter/lib ./lib
ENV PORT=8080
CMD ["./bin/ktor-starter"]