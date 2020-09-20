#!/bin/bash

if [ "$1" == "psql" ]
then
  docker pull postgres:latest
	docker_response=$( docker inspect postgres-0 | grep "Running" )
	if [[ "$docker_response" != *"\"Running\": true,"* ]]
	then
		docker run --name postgres-0 -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:latest
	fi
	docker exec -it postgres-0 bash 
else
	printf "usage: run_postgre.sh [option]\noptions:\n\t-psql\n"
fi








