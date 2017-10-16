#!/bin/bash

while true
do
  uuid=$(uuidgen)
  echo "$(date) :: Starting X-UUID=${uuid}"
  result=$(curl --silent -k -o /dev/null -w '%{http_code}' -H "X-UUID: ${uuid}" 'http://localhost:8888/helloworld')
  echo "$(date) :: ${uuid} :: ${result}"
  if [ "$result" -eq "000" ]
  then
    echo "Got 000 status back from curl, quitting loop"
    exit 1
  fi
done