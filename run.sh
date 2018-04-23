#!/bin/bash
set -e
source executables.sh

echo ""
echo "--- LAUNCH ---"
echo ""

$JAVA \
	--class-path out \
	$1
