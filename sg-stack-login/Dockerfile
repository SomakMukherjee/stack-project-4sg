FROM openjdk:latest
EXPOSE 8080
ADD target/service-login-0.0.1-SNAPSHOT.jar service-login-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["sh","-c","java -jar /service-login-0.0.1-SNAPSHOT.jar"]