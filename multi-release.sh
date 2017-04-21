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
$JAVAC --release 8 -d target/mr/classes-8 target/mr/src-8/*.java

echo "compile classes for Java 9"
sed 's/VersionDependent9/VersionDependent/g' \
	src/main/java/org/codefx/courses/java9/jvm/multi_release/VersionDependent9.java \
	> target/mr/src-9/VersionDependent.java
$JAVAC -d target/mr/classes-9 target/mr/src-9/*.java

echo "package"
$JAR --create --file target/mr/mr.jar -C target/mr/classes-8 . --release 9 -C target/mr/classes-9 .

# echo "run with Java 8:"
# java -cp target/mr/mr.jar java9.jvm.multi_release.Main

echo "run with Java 9:"
$JAVA --class-path target/mr/mr.jar org.codefx.courses.java9.jvm.multi_release.Main
