#!/bin/bash
set -e
source executables.sh

echo "TASK: CREATE CLASS LIST"
# HINT: the last command in this file launches this app
#       (run `mvn clean install` beforehand)
$JAVA \
	-XX:DumpLoadedClassList=app-cds.lst \
	--class-path target/java-x-1.0-SNAPSHOT.jar \
	org.codefx.courses.java9.api.stream.Iterate

echo "TASK: CREATE ARCHIVE"
$JAVA \
	-Xshare:dump \
	-XX:SharedClassListFile=app-cds.lst \
	-XX:SharedArchiveFile=app-cds.jsa \
	--class-path target/java-x-1.0-SNAPSHOT.jar

echo "TASK: LAUNCH APPLICATION WITH ARCHIVE AND MEASURE RUN TIME"
time $JAVA \
	-Xshare:on \
	-XX:SharedArchiveFile=app-cds.jsa \
	--class-path target/java-x-1.0-SNAPSHOT.jar \
	org.codefx.courses.java9.api.stream.Iterate

echo "OBSERVE: LAUNCH TIME IMPROVEMENT"
time $JAVA \
	--class-path target/java-x-1.0-SNAPSHOT.jar \
	org.codefx.courses.java9.api.stream.Iterate
