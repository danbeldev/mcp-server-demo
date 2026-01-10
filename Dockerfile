FROM gradle:8.10.2-jdk21 AS builder
WORKDIR /application

COPY gradle gradle
COPY gradlew .
COPY settings.gradle* .
COPY build.gradle* .

RUN --mount=type=cache,target=/home/gradle/.gradle \
    ./gradlew --no-daemon dependencies || true

COPY . .
RUN --mount=type=cache,target=/home/gradle/.gradle \
    ./gradlew --no-daemon clean build -x test

FROM bellsoft/liberica-openjre-debian:23.0.1 AS layers
WORKDIR /application
COPY --from=builder /application/build/libs/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM bellsoft/liberica-openjre-debian:23.0.1
WORKDIR /application
VOLUME /tmp

RUN useradd -ms /bin/bash spring-user
USER spring-user

COPY --from=layers /application/dependencies/ ./
COPY --from=layers /application/snapshot-dependencies/ ./
COPY --from=layers /application/spring-boot-loader/ ./
COPY --from=layers /application/application/ ./

ENTRYPOINT ["java","org.springframework.boot.loader.launch.JarLauncher"]
