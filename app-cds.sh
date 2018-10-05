#!/bin/bash
set -e
source executables.sh

echo "TASK: CREATE CLASS LIST"
# HINT: the last command in this file launches this app
#       (run `mvn clean install` beforehand)

echo "TASK: CREATE ARCHIVE"

echo "TASK: LAUNCH APPLICATION WITH ARCHIVE AND MEASURE RUN TIME"

echo "OBSERVE: LAUNCH TIME IMPROVEMENT"
time $JAVA \
	--class-path target/java-x-1.0-SNAPSHOT.jar \
	org.codefx.courses.java9.api.stream.Iterate
