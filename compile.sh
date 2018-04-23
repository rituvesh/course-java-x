#!/bin/bash
set -e
source executables.sh

echo "--- COMPILATION ---"
echo ""

rm -rf out/*
$JAVAC \
	-d out src/java9/**/**/*.java
