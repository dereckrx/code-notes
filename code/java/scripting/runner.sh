mkdir -p tempBuild
find . -name "*.java" | xargs javac -d tempBuild
mainClass="${1%%.*}"
java -cp tempBuild ${mainClass}
rm -r tempBuild
