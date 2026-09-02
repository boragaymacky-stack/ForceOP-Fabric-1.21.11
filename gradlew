#!/bin/sh
# Lightweight Gradle wrapper for ForceOP-Fabric-1.21.11
set -e
APP_HOME=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
GRADLE_VERSION=8.14.3
GRADLE_USER_HOME="${GRADLE_USER_HOME:-$HOME/.gradle}"
DIST_DIR="$GRADLE_USER_HOME/wrapper/dists/gradle-$GRADLE_VERSION"
DIST_ZIP="$DIST_DIR/gradle-$GRADLE_VERSION-bin.zip"
DIST_HOME="$DIST_DIR/gradle-$GRADLE_VERSION"
if [ ! -x "$DIST_HOME/bin/gradle" ]; then
  mkdir -p "$DIST_DIR"
  if [ ! -f "$DIST_ZIP" ]; then
    echo "Downloading Gradle $GRADLE_VERSION..."
    curl -fL --retry 3 -o "$DIST_ZIP" "https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip"
  fi
  rm -rf "$DIST_HOME.tmp"
  mkdir -p "$DIST_HOME.tmp"
  unzip -q "$DIST_ZIP" -d "$DIST_HOME.tmp"
  mv "$DIST_HOME.tmp/gradle-$GRADLE_VERSION" "$DIST_HOME"
  rmdir "$DIST_HOME.tmp" 2>/dev/null || true
fi
exec "$DIST_HOME/bin/gradle" "$@"
