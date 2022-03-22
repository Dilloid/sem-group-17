FROM openjdk:latest
COPY ./target/sem-group-17.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem-group-17.jar", "db:33060", "30000"]