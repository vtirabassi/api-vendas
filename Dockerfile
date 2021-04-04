FROM adoptopenjdk/openjdk11:alpine
COPY target/api-vendas-1.0-SNAPSHOT.jar /api-vendas-1.0-SNAPSHOT.jar

# set the startup command to execute the jar
CMD ["java", "-jar", "/api-vendas-1.0-SNAPSHOT.jar"]
