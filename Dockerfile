FROM openjdk:latest
COPY ./target/sem-group-17-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem-group-17-0.1.0.2-jar-with-dependencies.jar"]