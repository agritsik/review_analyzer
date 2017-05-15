# Review analyser

## Features Overview

Top users and products:
1. `InMemoryLeaderboard.java` - plain java code. Keeps data in map and uses java streams to order data.
1. `RedisLeaderboard.java` (not implemented) - redis-based solution. Redis supports leaderboard out of the box via ZINCRBY and ZRANGE commands.

Word count:
1. `SimpleTokenizer.java` - plain java code. Uses java streams to map and filter tokens.
1. `LuceneTokenizer.java` (not implemented) - Apache Lucene based solution. Elasticsearch and Solr are built on top Lucene.

Translator:
1. `GoogleTranslator.java` - uses Google REST API and keeps data in memory.

## How to run

1. Run tests and build a project `mvn clean package`
1. Run the application `java -Xmx512m -jar target/review-0.0.1-SNAPSHOT.jar`
1. Load initial data from `/tmp/1.txt` via `GET http://localhost:8080/load`. *Default path to file isn't configurable yet'*
1. Check top users `GET http://localhost:8080/users`
Expected response:
```
[
    "A3OXHLG6DIBRW8",
    "A1YUL9PCJR3JTY",
    "AY12DBB0U420B",
    "A281NPSIMI1C2R",
    "A1Z54EM24Y40LL",
    "A1TMAVN4CEM8U8",
    "A2MUGFV2TDQ47K",
    "A3TVZM3ZIXG8YW",
    "A3PJZ8TU8FDQ1K",
    "AQQLWCMRNDFGI"
]
```
1. Check top products `GET http://localhost:8080/products`
Expected response:
```
[
    "B007JFMH8M",
    "B002QWHJOU",
    "B002QWP8H0",
    "B002QWP89S",
    "B0026RQTGE",
    "B003B3OOPA",
    "B001EO5Q64",
    "B0026KPDG8",
    "B001RVFERK",
    "B001RVFEP2"
]
```
1. Translate a text `POST http://localhost:8080/translate` with body `{"text": "1"}`
Expected response: `je ne parle pas francais`

## Development approach

1. **Spring Boot** provides dependency injection, REST controllers and client ( e.g. MockMvc, RestTemplate and TestRestTemplate)
1. **RxJava** - reactive programming. For multithreading supports a set of useful Schedulers such as NewThread, Computation, IO, ThreadPool-based etc. Check `Flow.java` class for details

## Tests approach

1. Unit Tests - for each class e.g. `PlayerContextTest.java`
1. Integration Tests - for the flow e.g. `AppIntegrationTests.java`

## Performance tests and monitoring

1. Performance tests - TBD
1. Monitoring - jconsole, visualVM

![visualVM](/wiki/1.png)


## TODO
1.
