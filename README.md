# RBC UCI Dow Jones Index data set with spring-boot-2 webflux

## Building application

### Pre-requisites
- JDK 17+
- maven 3
- docker CLI

## Option 1 runing from local

```sh
    cd backend
    mvn clean package
    mvn spring-boot:run
    goto http://localhost:8080/webjars/swagger-ui/index.html
```

## Option 2: Using script

```sh
    ./backend/build.sh
    ./start.sh

```


## Option 3: Building Executable JAR
To create an `executable jar`, simply run:

```sh
 mvn clean package
```

#### To create a non-native docker image, simply run:

```sh
mvn clean spring-boot:build-image
```

#### To run the demo using docker, invoke the following:

```sh
docker run --rm -p 8080:8080 djs:1.0.0-SNAPSHOT
```
