FROM openjdk:11-jdk
CMD mvn clean package -Dmaven.test.skip=true
EXPOSE 8080
ARG JAR_FILE=target/m-cadastro-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]