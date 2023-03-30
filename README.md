## Getting started

Before running the service locally, you will need the following:
* Zulu OpenJDK 17
* Docker & Docker-compose
* Maven
* Makefile
* grpcurl

```
make up-local
mvn compile
```

You can start and debug api-domains application in IntelliJ (recommended IDE)

```
grpcurl --plaintext -d '{}' localhost:8000 acme.api.process_design.QueryService/ListSkills
```

```
make down-local           -> shut down the docker compose cluster
make down-local-deep      -> shut down the docker compose cluster and clean the volumes
```

## Steps to reproduce the issue

- Follow getting started
- mvn compile
- mvn spring-boot:start
- grpcurl --plaintext -d '{}' localhost:8000 acme.api.process_design.QueryService/ListSkills

Print
```
ERROR:
Code: Unknown
Message:
```

Instead of 
```
ERROR:
Code: Unknown
Message:
```

## Steps to reproduce Happy path

- Modify com.acme.apidomains.process_design.services.SkillServiceImpl in order to not throw any exception
- mvn compile
- grpcurl --plaintext -d '{}' localhost:8000 acme.api.process_design.QueryService/ListSkills
- 
Should print:
```
{
  "skills": [
    {
      "id": "2",
      "name": "api-rest",
      "description": "A skill to handle rest api call service tasks defined in business processes"
    },
    {
      "id": "1",
      "name": "email",
      "description": "A skill to handle email service tasks defined in business processes"
    }
  ]
}
```