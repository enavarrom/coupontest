FROM adoptopenjdk/openjdk11
VOLUME /tmp
EXPOSE 8082
ARG JAR_FILE=./build/libs/coupon-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} coupon-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/coupon-0.0.1-SNAPSHOT.jar"]