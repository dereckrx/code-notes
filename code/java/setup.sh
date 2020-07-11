brew cask install AdoptOpenJDK/openjdk/adoptopenjdk{8,13}
brew install jenv

export PATH="$HOME/.jenv/bin:$PATH"
eval "$(jenv init -)"

# ensure that JAVA_HOME is correct
jenv enable-plugin export
# make Maven aware of the Java version in use (and switch when your project does)
jenv enable-plugin maven

for version in 8 13
do
   jenv add /Library/Java/JavaVirtualMachines/adoptopenjdk-${version}.jdk/Contents/Home
done

echo ""
echo "Usage: Set global and local"
echo "jenv global 8.0"
echo "jenv local 13.0 # Creates .java-version in current directory"

echo ""
echo "## Add these lines to your .profile"
echo 'export PATH="$HOME/.jenv/bin:$PATH"'
echo 'eval "$(jenv init -)"'

echo ""
echo 'Run "jenv doctor" to verify install'
