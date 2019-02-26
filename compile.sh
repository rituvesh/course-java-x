#!/bin/bash
set -e
source executables.sh

echo "--- COMPILATION ---"
echo ""

rm -rf target
mkdir -p target/classes

$JAVAC --source 12 --enable-preview \
	-d target/classes src/main/java/org/codefx/courses/**/**/**/*.java
