## Issue

https://github.com/spring-projects-experimental/spring-modulith/issues/187

Looking at generated docs in `build/spring-modulith-docs` (or `src/main/asciidoc/index.adoc`), `SkillService` is depicted as a public service instead of an internal service of the module. It should not appear in the generated documentation unless we use the `CanvasOptions.revealInternals()` option:

```
new Documenter(modules).writeDocumentation(Documenter.DiagramOptions.defaults(), Documenter.CanvasOptions.defaults().revealInternals());
```

but it does.

## Getting started

Before running the service locally, you will need the following:
* Zulu OpenJDK 17
* Docker & Docker-compose
* Gradle
* Makefile
* grpcurl

### Build the project

```
./gradlew clean build --info
```

### Run the project

```
make up-local && ./gradlew clean bootRun --info
grpcurl --plaintext -d '{}' localhost:8000 acme.process_design.v1.QueryService/ListSkills
```
