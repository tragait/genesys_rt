# Building and running this project locally

## Building Java Archive

Following command builds all Java components to `genesys_rt/target/myproject-1.0-SNAPSHOT.jar` JAR file

```bash
mvn clean install
```

### Running from Jar Archive

Following command starts the application using JAR file:

```bash
  java -jar genesys_rt/target/myproject-1.0-SNAPSHOT.jar
```

### Running from IntelliJ IDE

Here are the steps to run or debug the application from Intellij:

1. Enable the desired maven profile form Maven Tool Window
2. Run a configuration from `Run -> Edit configurations` 

## Accessing services APIs

1. Register a sensor, where {sensorId} should be a BigDecimal
```
curl --location --request POST 'localhost:8080/v1/sensors/sensor/{sensorId}/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "city": "mycity",
    "country": "mycountry"
}'
```
2. Save Sensor data (Assistive service if kafka server not running)
```
curl --location --request POST 'localhost:8080/v1/sensors/data' \
--header 'Content-Type: application/json' \
--data-raw '{ 
  "sensorId":200,
  "temperature":1.2,
  "humidity":1.2,
  "timestamp":"2020-01-02 00:00:00.000"
}'
```
3. Fetch Sensor data by date list
```
curl --location --request GET 'localhost:8080/v1/sensors/query' \
--header 'Content-Type: application/json' \
--data-raw '{
    "ids": ["100", "200"],
    "parameters": ["temperature", "humidity"],
    "dates":["2020-01-01", "2020-01-02" ]
}'
```
4. Fetch Sensor data by date range
```
curl --location --request GET 'localhost:8080/v1/sensors/query' \
--header 'Content-Type: application/json' \
--data-raw '{
    "ids": ["100", "200"],
    "parameters": ["temperature", "humidity"],
    "dateRange":{ "from":"2020-01-01", "to":"2020-01-02"}
}'
```
## Fetch data from Kafka Broker

A kafka consumer service will run with the application to fetch event information from topic : sensorData

Configuration of kafka broker can be configured from genesys_rt/src/main/resources/application.yml

Message structure to consume data

{ "sensorId":100, "temperature":1.1, "humidity":1.1,"timestamp":"Tue, 6 Dec 2016 19:06:33 IST"}
