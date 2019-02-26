#!/bin/bash
set -e
source executables.sh

echo ""
echo "--- LAUNCH ---"
echo ""

$JAVA --enable-preview \
	--class-path target/classes \
	$1
