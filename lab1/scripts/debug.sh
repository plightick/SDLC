#!/bin/bash
set -e
cd "$(dirname "$0")/.."

echo "Компиляция с отладочной информацией..."
/usr/lib/jvm/java-21-openjdk-amd64/bin/javac -g -d out src/*.java

echo "Запуск в режиме отладки (порт 5005)."
echo "Дальше в VS Code: Run and Debug → «Attach к Java (порт 5005)» → F5"
echo "Потом в окне программы нажмите «Ввести данные»."
echo

exec "$(dirname "$0")/java-nosnap" \
  -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 \
  -cp out Main
