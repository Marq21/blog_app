FROM openjdk:17
ADD target/blog_app-0.0.1-SNAPSHOT.jar blog_app-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "blog_app-0.0.1-SNAPSHOT.jar"]