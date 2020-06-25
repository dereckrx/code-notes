---
title: Application Continuum
---

## Summary
Start all new projects with the 3 top level directories (V4)
* applications
* components
* databases
 
The first few steps happen in weeks versus months so it is easier to start every project with a component based directory structure.

For Kotlin or Java projects, this is the initial directory structure.

```
app
├── applications
├── components
├── databases
├── build.gradle
├── gradle
├── gradlew
├── gradlew.bat
├── manifest.yml
├── README.md
└── settings.gradle
```

========================================
## V1: Flat Directory
Single flat directory with all files

```
app/src/main/
└── ...all files...
```

## V2: Functional Groups (horizontal slicing)
Models, Controllers, Data Access Layer, and Utilities

```
app/src/main/
└── controllers
  └── StoryController.kt
└── dataaccess
  └── StoryRepo.kt
└── models
  └── Story.kt
  └── StoryRecord.kt
└── services
  └── RegistrationService.kt
└── utils
  └── JdbcTemplate.kt  
App.kt
```

## V3: Feature Groups (bounded contexts)
Files are starting to be organized by domain components.

```
app/src/main/
└── backlog
  └── StoryController.kt
  └── StoryRepo.kt
  └── Story.kt
  └── StoryRecord.kt
└── allocations
└── timesheets
└── users
└── accounts
└── projects 
└── jdbc-support
  └── DataSourceConfig.kt
  └── JdbcTemplate.kt
└── rest-support
  └── BasicHandler.kt
  └── RestTemplate.kt
App.kt
```

## V4: Components (single application)
In the v4 extracts domain concepts Users, Accounts, and Projects as components. 

```
└── app/src/main
  └── backlog
    └── StoryController.kt
    └── StoryRepo.kt
    └── Story.kt
    └── StoryRecord.kt
  └── allocations
  └── timesheets
  App.kt
└── components
  └── users/src/main
  └── accounts/src/main
  └── projects/src/main
  └── jdbc-support/src/main
  └── rest-support/src/main
  └── test-support/src/main
└── databases/continuum
  └── v1__initial_schema.sql
```

These are business domain concepts as apposed to app-domain concepts like allocations, backlog, timesheets. 
Business domain components could be extracted as libraries and used by multiple projects in the company.

In Domain Centric Architecture, app components can depend on on domain components, but not the other way around. 
This avoids circular dependencies.

```
interfaces -> app -> domain <- infra
```

Looking at our dependencies, our app components depend on domain components but none of our domain components depend on app components.

```
app
-> backlog
-> allocations
-> timesheets
-> accounts
  -> users
-> projects
-> users
-> jdbcsupport
  -> mysql
-> restsupport
  -> http
``` 

Components are extracted so that:

* they are pure business logic 
* contain no framework code (such as spring-boot server code)
* only depend on other components (no circular dependencies)
* Each component can be individually built and tested

A component could live on it's own and be used like a library.
And at this point, the jdbc-support and reset-support components start to look like libraries.

Each component defines it's own dependencies in it's `build.gradle`.
So mysql dependencies move into the jdbc-support component and rest dependencies move into rest-support.
The `build.gradle` of app/src/main/backlog then defines only the components as dependencies.

```
dependencies {
    compile project(":components:jdbc-support")
    compile project(":components:rest-support")
    compile project(":components:test-support")

    compile project(":components:accounts")
    compile project(":components:projects")
    compile project(":components:users")
}
```

## V5: Multiple Applications (single database)
The v5 commit introduces 4 distinct applications - Allocations, Backlog, Registration, and Timesheets.

However, they still share a single database. So they can still grab objects
without using HTTP service-to-service communication.

## V6: Microservices: Service-to-Service communication (still same DB)
Use http clients instead of db clients to get other component's data

## V7: Miroservices with individual databases
Each microservice has it's own database

## V8+: Service discovery, Circuit Breaker, Distributed System 
Services are not hardcoded in the manifest/config file, 
instead they use a service discover client to find the service url, 
and now load balancing is introduced.

---
## Sources
[application-continuum github repo](https://github.com/continuumcollective/application-continuum)
[Pal Training](https://waveland.education.pivotal.io/cnd-course/)
