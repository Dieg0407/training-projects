FROM maven:3-eclipse-temurin-21 AS build

RUN mkdir /usr/src/project
COPY . /usr/src/project
WORKDIR /usr/src/project

RUN mvn package -DskipTests
RUN jdeps --ignore-missing-deps -q \
    --recursive \
    --multi-release 21 \
    --print-module-deps \
    --class-path 'target/deps/*' \
    target/app.jar > deps.info

RUN jlink --add-modules $(cat deps.info) \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /jre

FROM debian:buster-slim
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"
COPY --from=build /jre $JAVA_HOME

RUN mkdir /opt/app
COPY target/app.jar /opt/app
CMD ["java", "-jar", "/opt/app/app.jar"]
