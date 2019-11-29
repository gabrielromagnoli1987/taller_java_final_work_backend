# Use this config while you are in development (build your .jar on the host machine)
#FROM openjdk:11
#COPY target/petclinic-0.0.1-SNAPSHOT.jar /app/target/petclinic-0.0.1-SNAPSHOT.jar

# Use this multi-stage build config for production (https://www.youtube.com/watch?v=t2cDtDrNqc8)
FROM maven:3.6-jdk-11 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn -e -B package -DskipTests

FROM openjdk:11
COPY --from=builder /app/target/petclinic-0.0.1-SNAPSHOT.jar /app/target/