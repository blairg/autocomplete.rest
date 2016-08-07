FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Blair Garrett <blair.garrett1@gmail.com>
VOLUME /tmp
EXPOSE 8080
EXPOSE 27017
ADD build/libs/autocomplete.rest-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]