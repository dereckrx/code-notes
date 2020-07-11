# ---
# tags: [curl]
# sources: [http://www.compciv.org/recipes/cli/jq-for-parsing-json/]
# ---

# Setup: `brew install jq`
# Use with curl

echo "Pretty print full response '.'"
cat data.json | jq '.'

echo ""
echo "Select key"
cat data.json | jq '.id'

echo ""
echo "Select multiple keys"
cat data.json | jq '.id, .name'

echo ""
echo "Select nested keys and array indexes"
cat data.json | jq '.posts[0].title'

echo ""
echo "Select all items in array"
cat data.json | jq '.posts[].title'
