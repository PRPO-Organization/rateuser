#!/bin/bash

BASE_URL="http://localhost:8080"

echo "=== Testing GET /api/ratings/users/1 ==="
curl -s "${BASE_URL}/api/ratings/users/1" | jq '.'

echo -e "\n=== Testing POST /api/ratings/1 ==="
curl -s -X POST "${BASE_URL}/api/ratings/1" \
  -H "Content-Type: application/json" \
  -d '{"ratedUserId": 5, "userRating": 5, "comment": "Excellent!"}' | jq '.'

echo -e "\n=== Testing GET /api/ratings/1 ==="
curl -s "${BASE_URL}/api/ratings/1" | jq '.'

echo -e "\n=== Testing GET /api/ratings/1/comments ==="
curl -s "${BASE_URL}/api/ratings/1/comments" | jq '.'
