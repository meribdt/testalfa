## ON SERVER
### Requirements
JAVA 11 runtime:
```sh
$ sudo apt-get install openjdk-11-jre-headless
```

### Prepare
*Create directory*
```sh
$ sudo mkdir {destination}
```
*Put project files in the directory*
```sh
$ sudo cp {origin} {destination}
```
*Edit permissions*
```sh
$ sudo chmod 755 gradlew
```

### Run
*Run gradlew using command*
```sh
$ ./gradlew bootRun
```
### [Option] Run Jar compiled
*Build jar file using command*
```sh
$ ./gradlew bootJar
```
*Run jar*
```sh
$ java -jar *PATH_TO_WORKDIR*/bild/libs/*FILENAME*.jar
```

### Test
*Make get request*
```sh
$ curl -X GET http://127.0.0.1:7888/ratechange/USD
```

## DOCKER
### Image
*Build docker image*
```sh
$ cd *PATH_TO_WORKDIR*
$ docker build -t alfa
```
*Check image is present*
```sh
$ docker images
```
### Container
*Run container*
```sh
$ docker run --name alfa-test -p 3000:7888 -d alfa
```
*Check container is launched*
```sh
$ docker ps
```
### [Option] Restart container
```sh
$ docker update --restart unless-stopped alfa-test
```
### Test
*Make get request*
```sh
$ curl -X GET http://127.0.0.1:3000/ratechange/USD
```
