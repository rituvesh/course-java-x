#!/bin/bash
set -e
source executables.sh

echo " > creating clean directories"
rm -rf out/classes
mkdir -p out/classes
rm -rf out/mods
mkdir -p out/mods

echo " > compiling and packaging logger"
mkdir out/classes/logger
$JAVAC -d out/classes/logger src-logging/java9/jvm/platform_logging/logger/*.java
$JAR --create --file out/mods/logger.jar -C out/classes/logger/ .

echo " > compiling and packaging app"
mkdir out/classes/app
$JAVAC -d out/classes/app src-logging/java9/jvm/platform_logging/app/*.java
$JAR --create --file out/mods/app.jar --main-class java9.jvm.platform_logging.app.LoggingApplication -C out/classes/app/ .

echo " > running App"
$JAVA -verbose:gc -p out/mods -m app
