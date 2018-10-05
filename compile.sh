#!/bin/bash
set -e
source executables.sh

echo "--- COMPILATION ---"
echo ""

rm -rf target
mkdir -p target/classes

$JAVAC \
	-d target/classes src/main/java/org/codefx/courses/java9/**/**/*.java
