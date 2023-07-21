FROM maven:latest

WORKDIR /app

COPY target/ypc.jar ypc.jar

ENTRYPOINT [ "java", "-jar", "/ypc.jar" ]