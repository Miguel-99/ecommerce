FROM gradle:7.5.1-jdk17-alpine as BUILDER

WORKDIR /app

ADD . .

RUN gradle shadowJar

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=BUILDER /app/build/libs/ecommerce.jar ecommerce.jar

CMD ["java", "-jar", "ecommerce.jar"]
