FROM openjdk:11
ADD target/petclinic-0.0.1-SNAPSHOT.jar /app/target/petclinic-0.0.1-SNAPSHOT.jar
RUN mkdir /app/images/