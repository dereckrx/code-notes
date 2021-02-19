# Find filenames that match pattern
find . -iname "*test*"

# Find text in files
find . -iname "*.js" | xargs grep "TODO" --context=1

# Count Occurances of text in files
find . -iname '*' | xargs grep "TODO" -c

# Search for text in files
find . -iname '*' | xargs grep "foo" --color --context

#=== Grep ================================
# Grep for error and date
grep -i "error" *.log | grep -i "2016-11-30"
