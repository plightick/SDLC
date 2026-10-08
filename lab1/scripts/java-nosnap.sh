#!/bin/bash
# VS Code из Snap подставляет свои glibc-библиотеки — системный Java из-за этого падает.
export LD_LIBRARY_PATH=
export LD_PRELOAD=
exec /usr/lib/jvm/java-21-openjdk-amd64/bin/java "$@"
