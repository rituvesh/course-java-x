#!/bin/bash
set -e
source executables.sh

rm -rf target
mkdir -p target/mr/src-8
mkdir -p target/mr/classes-8
mkdir -p target/mr/src-9
mkdir -p target/mr/classes-9

echo "compile classes for Java 8"
sed 's/VersionDependent8/VersionDependent/g' \
	src/main/java/org/codefx/courses/java9/jvm/multi_release/VersionDependent8.java \
	> target/mr/src-8/VersionDependent.java
sed 's/VersionDependent8/VersionDependent/g' \
	src/main/java/org/codefx/courses/java9/jvm/multi_release/Main.java \
	> target/mr/src-8/Main.java

echo "TASK: COMPILE THE CLASSES IN target/mr/src-8 AGAINST JAVA 8"

echo "compile classes for Java 9"
sed 's/VersionDependent9/VersionDependent/g' \
	src/main/java/org/codefx/courses/java9/jvm/multi_release/VersionDependent9.java \
	> target/mr/src-9/VersionDependent.java
echo "TASK: COMPILE THE CLASSES IN target/mr/src-9 AGAINST JAVA 9"

echo "TASK: PACKAGE AND RUN ON JAVA 8 AND JAVA 9"
