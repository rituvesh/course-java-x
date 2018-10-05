#!/bin/bash
set -e
source executables.sh

echo "OBSERVE: RUN `java -Xlog:help` AND FAMILIARIZE YOURSELF WITH THE HELP"

echo "----- ----- ----- ----- -----"
echo "TASK: SHOW ALL info MESSAGES TAGGED ONLY WITH gc"
$JAVA -version

echo "----- ----- ----- ----- -----"
echo "TASK: SHOW ALL info MESSAGES TAGGED WITH gc"
$JAVA -version

echo "----- ----- ----- ----- -----"
echo "TASK: SHOW ALL MESSAGES TAGGED WITH gc"
$JAVA -version

# TASK: Make sense of these MESSAGES (nah, just kidding ;)

echo "----- ----- ----- ----- -----"
echo "TASK: DIVERT ALL gc-MESSAGES INTO A FILE `gc.log`"
$JAVA -version

echo "----- ----- ----- ----- -----"
echo "TASK: SHOW date&time, level, AND thread id OF ALL gc-MESSAGES"
$JAVA -version
