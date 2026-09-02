#!/bin/sh
set -e
printf '\n=== ForceOP Fabric 1.21.11 Build ===\n\n'
./gradlew clean build --no-daemon
printf '\n=== Build complete ===\n\nJAR files:\n'
ls -lh build/libs/*.jar
