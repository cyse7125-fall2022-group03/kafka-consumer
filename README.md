# kafka-consumer
kafka consumer code with elastic search

| Name                | NUID      | Email                          |
| ------------------- | --------- | ------------------------------ |
| Ketki Kule          | 001549838 | kule.k@northeastern.edu        |
| Sandeep Wagh        | 001839964 | wagh.sn@northeastern.edu       |
| Vignesh Gunasekaran | 001029530 | gunasekaran.v@northeastern.edu |

This repo contains kafka consumer code with elastic search
    
## Commands:

First have docker logged in
```
cd kafka-consumer
docker login
docker images
```
Create a docker image for this web application:
```
docker build --platform linux/amd64 -t consumerapp40 .
```
Tag that image to the private repository:
```
docker tag consumerapp40 csye7125fall2022group03/dockrepo:consumerapp40
```
Push the tag to the private repository:
```
docker push csye7125fall2022group03/dockrepo:consumerapp40
```

