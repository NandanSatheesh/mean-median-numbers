FROM maven:slim as build
COPY . /
RUN  mvn clean install -DskipTests

FROM openjdk:8
COPY --from=build /target/*.jar .
EXPOSE 8080
CMD java -Xms300m -Xmx1200m -jar numbers-0.0.1-SNAPSHOT.jar --server.port=8080
