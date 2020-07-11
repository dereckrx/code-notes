Setup
* `brew install jq`

```
curl -X POST \
  https://jsonplaceholder.typicode.com/posts \
  -H 'Content-type: application/json' \
  -d '{
  "title": "foo",
  "body": "bar",
  "userId": 1
}' | jq
```
