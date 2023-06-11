FROM openjdk:17
EXPOSE 8080 8000

ARG JAR_FILE
ENV JAR_FILE ${JAR_FILE}
CMD java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 -jar /usr/share/${JAR_FILE}

ARG GIT_COMMIT
ARG GIT_BRANCH
ARG GIT_TAGS
ARG BUILD_TIME
ARG PROJECT_VERSION
LABEL git-commit=${GIT_COMMIT} \
      git-branch=${GIT_BRANCH} \
      git-tags=${GIT_TAGS} \
      build-timestamp=${BUILD_TIME} \
      project-version=${PROJECT_VERSION}
