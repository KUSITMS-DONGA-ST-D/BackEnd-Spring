FROM openjdk:17
ARG JAR_FILE=./build/libs/DONGA-ST-D-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--spring.profiles.active=prod"]