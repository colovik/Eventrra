#!/bin/sh

# Use netcat to check MongoDB port availability
until nc -zv mongo_db 27017; do
  >&2 echo "MongoDB is unavailable - sleeping"
  sleep 2
done
>&2 echo "MongoDB is ready!"