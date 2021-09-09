#!/bin/bash
#checks the number of argument
CMD=$1
USERNAME=$2
PASSWORD=$3

#check container status
docker_status=$(docker container ls -a -f name=jrvs-psql | wc -l)


#checks user input and use switch to execute command
case $CMD in
	# creates a psql container
	create)
	if [ $(docker_status) -eq 2 ]
	then
		echo "Error: Container Exists"
		exit 1
	fi

	# check if username and password are passed
	if [ "$#" -ne 3 ]
	then
		echo "Error: Please provide a username and password"
		exit 1
	fi

	#  create a volume for container
	docker volume create pgdata

	# create the new container using the USERNAME and PASSWORD given
	docker run --name jrvs-psql -e POSTGRES_PASSWORD=${PASSWORD} -e POSTGRES_USER=${USERNAME} -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
	exit $?
	;;

	# starts the container
	start)
	if [ $(docker_status) -ne 2 ]
	then
		echo "Error: Container does not exist"
		exit 1
	fi
	docker container start jrvs-psql
	echo "container started"
	exit 0
	;;

	# stops the container
	stop)
	  #checks if container exists or not
	if [ $(docker_status) -ne 2 ]
	then
		echo "Error: Container does not exist"
		exit 1
	fi
	docker container stop jrvs-psql
	echo "container stopped"
	exit 0
	;;

	# catch the  invalid input
	*)
	echo "Error: Invalid input"
	exit 1
	;;

esac
exit 0


