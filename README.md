## Issue

https://github.com/spring-projects-experimental/spring-modulith/issues/187

Looking at generated docs in `build/spring-modulith-docs` (or `src/main/asciidoc/index.adoc`) only Skill service is depicted, not `ProcessDesignService` nor `SkillRepository`

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
