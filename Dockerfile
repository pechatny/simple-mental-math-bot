FROM openjdk:17-oracle AS build
ARG TOKEN
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN --mount=type=cache,target=/root/.m2 ./mvnw -f $HOME/pom.xml clean package -DVAULT_TOKEN=${TOKEN}

FROM openjdk:17-oracle
ARG TOKEN
ENV VAULT_TOKEN=$TOKEN
ENV ENVIRONMENT_PROFILE_NAME=prod
ARG JAR_FILE=/usr/app/target/*.jar
COPY --from=build $JAR_FILE /app/runner.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/runner.jar"]