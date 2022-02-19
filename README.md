# Historical data analytic service

> [![CI/CD](https://github.com/fragaLY/analytic-service/actions/workflows/ci-cd.yml/badge.svg)](https://github.com/fragaLY/analytic-service/actions/workflows/ci-cd.yml)

### How to run in develop mode
1. Separate run for backend and frontend parts
- run ```gradle bootRun```
- run ```cd frontend``` and ```npm start```
- [Liveness](http://localhost:3000)
- [Liveness](http://localhost/actuator/health/liveness)
- [Readiness](http://localhost/actuator/health/readiness)

2. Run full application
- run ```gradle clean build``` and ```java -jar build/libs/application.jar```