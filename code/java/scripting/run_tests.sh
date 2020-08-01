curl -O https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.6.2/junit-platform-console-standalone-1.6.2.jar
mkdir -p deps
mv junit-platform-console-standalone-1.6.2.jar deps/junit-platform-console-standalone-1.6.2.jar

java -jar deps/junit-platform-console-standalone-1.6.2.jar
