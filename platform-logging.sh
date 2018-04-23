#!/bin/bash
set -e
source executables.sh

echo " > creating clean directories"
rm -rf target
mkdir -p target/logging/app-classes
mkdir -p target/logging/log-classes
mkdir -p target/logging/mods

echo " > compiling and packaging logger"
$JAVAC -d target/logging/log-classes src/main/logging/java9/jvm/platform_logging/logger/*.java
$JAR --create --file target/logging/mods/logger.jar -C target/logging/log-classes/ .

echo " > compiling and packaging app"
$JAVAC -d target/logging/app-classes src/main/logging/java9/jvm/platform_logging/app/*.java
$JAR --create --file target/logging/mods/app.jar --main-class java9.jvm.platform_logging.app.LoggingApplication -C target/logging/app-classes .

echo " > running app"
$JAVA --module-path target/logging/mods -m app
