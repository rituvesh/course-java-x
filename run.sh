#!/bin/bash
set -e
source executables.sh

echo ""
echo "--- LAUNCH ---"
echo ""

$JAVA \
	--class-path target/classes \
	$1
