FROM  gradle:7.5.1-jdk17-alpine as BUILDER

WORKDIR /app

ADD . .

RUN gradle shadowJar

FROM openjdk:17-alpine

WORKDIR /app

COPY --FROM = BUILDER /app/build/libs/app-1.0-SNAPSHOT-all.jar ecommerce.jar

CMD ["java", "jar", "ecommerce.jar"]




