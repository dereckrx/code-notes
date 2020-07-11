# http://thecodebarbarian.com/what-javascript-developers-should-know-about-curl.html

# -i    Include response headers
# Auth: -H "Authorization: my-secret-token"

curl -X POST \
  https://jsonplaceholder.typicode.com/posts \
  -H 'Content-type: application/json' \
  -d '{
  "title": "foo",
  "body": "bar",
  "userId": 1
}'

# Post using data file
echo ""; echo ""
curl -X POST \
  https://jsonplaceholder.typicode.com/posts \
  -H 'Content-type: application/json' \
  -d '@../data.json'

# Post using data file to std-in
echo ""; echo ""
cat '../data.json' | curl --data "@-" -X POST \
  https://jsonplaceholder.typicode.com/posts \
  -H 'Content-type: application/json'

