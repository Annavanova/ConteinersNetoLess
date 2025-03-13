FROM openjdk:17
EXPOSE 8080
ADD build/libs/SpringBootdemo-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]