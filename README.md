### Requirements
JAVA 11 runtime:
```sh
$ sudo apt-get install openjdk-11-jre-headless
```

### How to run
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

*Run gradlew using command*
```sh
$ ./gradlew bootRun
```
*Make get request*
```sh
$ curl -X GET http://127.0.0.1:7888/ratechange/USD
```
