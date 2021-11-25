FROM azul/zulu-openjdk-alpine:11.0.13-jre-headless
ENV JAR_NAME aspect-oriented-programming.jar
COPY build/libs/$JAR_NAME $JAR_NAME
EXPOSE 6000
ENTRYPOINT java -jar $JAR_NAME $JAR_NAME