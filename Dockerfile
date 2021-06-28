FROM gradle:7.1-jdk11 as app-builder

WORKDIR /usr/src/app

RUN addgroup --gid 1930 gradleuser && \
    adduser --system --ingroup gradleuser --uid 1930 --disabled-password gradleuser && \
    chown -R gradleuser:gradleuser /usr/src/app

USER gradleuser

COPY --chown=gradleuser:gradleuser gradle gradle
COPY --chown=gradleuser:gradleuser build.gradle.kts .
COPY --chown=gradleuser:gradleuser settings.gradle.kts .
COPY --chown=gradleuser:gradleuser src src

RUN gradle :build --no-daemon

###################################
FROM adoptopenjdk/openjdk11:alpine

WORKDIR /usr/src/app

RUN addgroup -g 1930 appuser && \
    adduser -SD -u 1930 appuser appuser && \
    chown -R appuser:appuser /usr/src/app

USER appuser

COPY --chown=appuser:appuser --from=app-builder /usr/src/app/build/libs/photo-album.jar .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "photo-album.jar"]
