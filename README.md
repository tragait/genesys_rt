# Building and running this project locally

## Building Java Archive

Following command builds all Java components to `accela_rt/target/myproject-1.0-SNAPSHOT.jar` JAR file

```bash
mvn clean install
```

### Running from Jar Archive

Following command starts the application using JAR file:

```bash
  java -jar accela_rt/target/myproject-1.0-SNAPSHOT.jar
```

### Running from IntelliJ IDE

Here are the steps to run or debug the application from Intellij:

1. Enable the desired maven profile form Maven Tool Window
2. Run a configuration from `Run -> Edit configurations` 

## Accessing services

Swagger UI and API specifications are available to discover service endpoints and send requests.

* `http://localhost:8080/swagger-ui/index.html`

