FROM openjdk:11
COPY target/ /app/target/
RUN mkdir /app/images/