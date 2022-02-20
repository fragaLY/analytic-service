# Historical data analytic service

### How to run in develop mode
1. Separate run for backend and frontend parts
- run ```gradle bootRun```
- [Aggregate](http://localhost/api/teams/aggregate)
- [Liveness](http://localhost/actuator/health/liveness)
- [Readiness](http://localhost/actuator/health/readiness)

2. Run using gradle and java application
- run ```gradle clean build``` and ```java -jar build/libs/application.jar```

3. Run docker image
- run ```gradle clean jibDockerBuild``` and ```docker run -p 80:80 fragaly/analytic-service```