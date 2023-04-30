## Issue

Did not find how to make Modulith transactional events work in a R2DBC context. We tested:
- `org.springframework.experimental:spring-modulith-jdbc` required a jdbcTemplate to work properly
- Adding `org.springframework.boot:spring-boot-starter-data-jdbc` or simply `org.springframework.boot:spring-boot-starter-jdbc` with `spring.datasource.xxx` configurations in `application.properties` failed. Probably because of `org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration` which works only when `@ConditionalOnMissingBean(type = "io.r2dbc.spi.ConnectionFactory")` is satisfied...
- So we provided a Datasource and a jdbcTemplate manually. But the reactive transaction manager throw an exception because of `org.springframework.experimental:spring-modulith-jdbc` : `java.lang.IllegalStateException: Cannot apply reactive transaction to non-reactive return type: interface java.util.List`

We are waiting for:
- https://github.com/spring-projects-experimental/spring-modulith/issues/174

In the meantime we only use [non-transactional events](https://github.com/vincentditlevinz/api-domains-issue/blob/8d8a22233ad941783a64fa83c36589bd4d434178/src/main/java/com/acme/apidomains/process_runtime/ProcessRuntimeQueryHandler.java#L21)

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


### Tips

You can show internals components with the following configuration:

```
new Documenter(modules).writeDocumentation(Documenter.DiagramOptions.defaults(), Documenter.CanvasOptions.defaults().revealInternals());
```
