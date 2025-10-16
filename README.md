# Ktor Starter

## Live Demo [https://ktor-starter-yqc0x.sevalla.app](https://ktor-starter-yqc0x.sevalla.app)

A simple, modern starter template for building server-side applications with [Ktor](https://ktor.io/) in Kotlin. This project serves as a boilerplate to help you quickly initialize a new Ktor application, with everything you need to get started: a basic configuration, organized source structure, and essential Gradle setup.

## Features

- **Ktor Framework**: Harness the full power of Kotlin’s popular, asynchronous web framework.
- **Modular Structure**: Clean separation between application code, configuration, and resources for scalability.
- **Out-of-the-box Development Tools**:
    - Sample [application.conf](src/main/resources/application.conf) for environment settings
    - Logging set up with [Logback](src/main/resources/logback.xml)
    - Static file serving ready to use
- **Gradle Kotlin DSL**: Modern build setup with easy extensibility.
- **Ready for Deployment**: Easily adapts to your production or local development needs.

## Getting Started

1. **Clone the repository:**
```shell script
git clone https://github.com/yourusername/ktor-starter.git
    cd ktor-starter
```


2. **Run the application:**
```shell script
./gradlew run
```

    The server will start using the configuration in `application.conf`.

3. **Open in browser:**  
   Visit [http://localhost:8080](http://localhost:8080) (or the port defined in your config).

## Structure

```
ktor-starter/
 ├─ src/
 │   ├─ main/
 │   │   ├─ kotlin/               # Kotlin source files
 │   │   └─ resources/            # Config, static files, logging
 │   └─ test/                     # Test sources
 ├─ build.gradle.kts              # Gradle build config (Kotlin DSL)
 ├─ README.md
 └─ ...                           # Other supporting files
```


## Customization

- Update routes and logic in `src/main/kotlin/`.
- Extend configuration in `application.conf`.
- Add static assets to `src/main/resources/static`.
- Configure logging via `logback.xml`.

## License

This project is released under the [MIT License](LICENSE).

---