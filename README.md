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

3. Run docker image
- run ```gradle clean jibDockerBuild``` and ```docker run -p 80:80 fragaly/analytic-service```

4. How to create a GCP VM instance with container via command line:
```
gcloud compute instances create-with-container analytic-service --project=veekay-demo --zone=europe-west1-b --machine-type=f1-micro --network-interface=network-tier=PREMIUM,subnet=default --maintenance-policy=MIGRATE --provisioning-model=STANDARD --service-account=961057803682-compute@developer.gserviceaccount.com --scopes=https://www.googleapis.com/auth/devstorage.read_only,https://www.googleapis.com/auth/logging.write,https://www.googleapis.com/auth/monitoring.write,https://www.googleapis.com/auth/servicecontrol,https://www.googleapis.com/auth/service.management.readonly,https://www.googleapis.com/auth/trace.append --tags=http-server --image=projects/cos-cloud/global/images/cos-stable-93-16623-102-12 --boot-disk-size=10GB --boot-disk-type=pd-balanced --boot-disk-device-name=analytic-service --container-image=docker.io/fragaly/analytic-service:latest --container-restart-policy=always --no-shielded-secure-boot --shielded-vtpm --shielded-integrity-monitoring --labels=container-vm=cos-stable-93-16623-102-12
```