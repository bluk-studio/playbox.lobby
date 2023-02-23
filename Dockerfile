# 
# Builder
FROM openjdk:18-buster as builder
WORKDIR /usr/src/app

# Copying everyhing
COPY . .

# Building our artifact
RUN ./gradlew build

# 
# Runtime
FROM openjdk:18 as runtime
WORKDIR /usr/src/app

COPY --from=builder usr/src/app/build/libs/server.jar .

ENTRYPOINT [ "java", "-Xmx512M", "-jar", "server.jar" ]