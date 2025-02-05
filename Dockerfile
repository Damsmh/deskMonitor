FROM maven:latest AS stage1
WORKDIR /deskMonitor
COPY pom.xml /deskMonitor
RUN mvn dependency:resolve
COPY . /deskMonitor
RUN mvn clean
RUN  mvn package -DskipTests

FROM openjdk:23 AS final
COPY --from=stage1 /deskMonitor/target/*.jar deskMonitor.jar
EXPOSE 8080
CMD ["java", "-jar", "deskMonitor.jar"]